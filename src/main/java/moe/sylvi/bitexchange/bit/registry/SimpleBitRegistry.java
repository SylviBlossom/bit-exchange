package moe.sylvi.bitexchange.bit.registry;

import com.google.common.collect.Lists;
import moe.sylvi.bitexchange.BitExchange;
import moe.sylvi.bitexchange.bit.BitHelper;
import moe.sylvi.bitexchange.bit.Recursable;
import moe.sylvi.bitexchange.bit.info.BitInfo;
import moe.sylvi.bitexchange.bit.registry.builder.BitRegistryBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class SimpleBitRegistry<R,I extends BitInfo<R>> implements BitRegistry<R,I> {
    public static boolean DEBUGGING = false;

    private final Logger logger;
    private final Registry<R> resourceRegistry;
    private final I emptyInfo;

    private final HashMap<R, I> infoMap = new HashMap<>();
    private final List<I> infoList = new ArrayList<>();

    private final List<BitRegistryBuilder<R,I>> builders = new ArrayList<>();
    private final HashMap<R, List<BitRegistryBuilder<R,I>>> resourceProcessors = new HashMap<>();
    private final HashSet<RecursivePair<R,I>> recursiveCheck = new HashSet<>();

    private BitRegistryBuilder<R,I> currentProcessor;
    private boolean processing;

    public SimpleBitRegistry(Registry<R> resourceRegistry, I emptyInfo) {
        this.resourceRegistry = resourceRegistry;
        this.emptyInfo = emptyInfo;
        this.logger = LogManager.getLogger();
    }

    @Override
    public void registerBuilder(BitRegistryBuilder<R,I> builder) {
        builders.add(builder);
    }

    @Override
    public void prepareResource(R resource, BitRegistryBuilder<R,I> builder) {
        var list = resourceProcessors.computeIfAbsent(resource, i -> new ArrayList<>());
        if (!list.contains(builder)) {
            list.add(builder);
        }
        list.sort(Comparator.comparing(BitRegistryBuilder::getPriority));
    }

    @Override
    public Registry<R> getResourceRegistry() {
        return resourceRegistry;
    }

    @Override
    public I getEmpty() {
        return emptyInfo;
    }

    @Override
    public void preBuild(MinecraftServer server) {
        logger.log(Level.INFO, "Preparing bit registry builders");
        processing = true;
        infoMap.clear();
        infoList.clear();
        for (BitRegistryBuilder<R,I> builder : builders) {
            try {
                builder.prepare(server);
            } catch (Exception e) {
                logger.warn("Error in preparing " + builder.getClass().getSimpleName(), e);
            }
        }
    }

    @Override
    public void build() {
        logger.log(Level.INFO, "Building bit values");
        for (R resource : resourceProcessors.keySet()) {
            try {
                if (!infoMap.containsKey(resource)) {
                    process(resource, false);
                }
            } catch (Exception e) {
                String errorMessage = "Error in processing resource " + resource.toString();
                if (currentProcessor != null) {
                    errorMessage += " [" + currentProcessor.getClass().getSimpleName() + "]";
                    currentProcessor = null;
                }
                logger.warn(errorMessage, e);
            }
            recursiveCheck.clear();
            DEBUGGING = false;
        }
    }

    @Override
    public void postBuild() {
        for (BitRegistryBuilder<R,I> builder : builders) {
            try {
                builder.postProcess();
            } catch (Exception e) {
                logger.warn("Error in post-processing " + builder.getClass().getSimpleName(), e);
            }
        }
        for (I info : infoMap.values()) {
            if (info.getValue() > 0) {
                infoList.add(info);
            }
        }
        processing = false;
        resourceProcessors.clear();
        logger.log(Level.INFO, "Built " + infoList.size() + " bit values");
    }

    private Recursable<I> process(R resource, boolean allowFallback) {
        var itemId = BitHelper.getItemId(resource, this);
        var wasDebugging = DEBUGGING;
        if (!wasDebugging && BitHelper.isDebugging(resource, this)) {
            BitExchange.log(Level.INFO, "[DEBUG] Begin processing " + BitHelper.DEBUG_ITEM);
            DEBUGGING = true;
        }
        if (resourceProcessors.containsKey(resource) && !resourceProcessors.get(resource).isEmpty()) {
            boolean recursedAll = true;
            for (BitRegistryBuilder<R,I> builder : resourceProcessors.get(resource)) {
                if (DEBUGGING) {
                    BitExchange.log(Level.INFO, "[DEBUG] Processing " + itemId + " with " + builder.getClass().getSimpleName());
                }
                RecursivePair<R,I> recursivePair = new RecursivePair<>(resource, builder);
                if (recursiveCheck.contains(recursivePair)) {
                    if (!allowFallback) {
                        if (DEBUGGING) {
                            BitExchange.log(Level.INFO, "[DEBUG] Recursive check failed, stopping");
                            DEBUGGING = wasDebugging;
                        }
                        return Recursable.of(null, true);
                    } else {
                        if (DEBUGGING) {
                            BitExchange.log(Level.INFO, "[DEBUG] Found recursion, falling back");
                        }
                        continue;
                    }
                } else {
                    recursedAll = false;
                    recursiveCheck.add(recursivePair);
                }
                boolean topProcessor = currentProcessor == null;
                currentProcessor = topProcessor ? builder : currentProcessor;
                I result = builder.process(resource);
                currentProcessor = topProcessor ? null : currentProcessor;
                if (result != null) {
                    if (DEBUGGING) {
                        BitExchange.log(Level.INFO, "[DEBUG] Successfully processed " + itemId + " (value: " + result.getValue() + ")");
                        DEBUGGING = wasDebugging;
                    }
                    add(result);
                    recursiveCheck.remove(recursivePair);
                    return Recursable.of(result, false);
                }
                recursiveCheck.remove(recursivePair);
            }
            if (recursedAll) {
                if (DEBUGGING) {
                    BitExchange.log(Level.INFO, "[DEBUG] Recursed through all available processors, stopping");
                    DEBUGGING = wasDebugging;
                }
                return Recursable.of(null, true);
            }
        }
        if (DEBUGGING) {
            BitExchange.log(Level.INFO, "[DEBUG] No value found for " + itemId);
            DEBUGGING = wasDebugging;
        }
        return Recursable.of(null, false);
    }

    @Override
    public void add(I info) {
        infoMap.put(info.getResource(), info);
    }

    @Override
    public I get(R resource) {
        return infoMap.get(resource);
    }

    @Override
    public Recursable<I> getOrProcess(R resource, boolean allowFallback) {
        if (processing && !infoMap.containsKey(resource)) {
            return process(resource, allowFallback);
        } else {
            return Recursable.of(get(resource), false);
        }
    }

    @NotNull
    @Override
    public Iterator<I> iterator() {
        return infoList.iterator();
    }

    @Override
    public List<I> getList() { return infoList; }

    @Override
    public void load(List<I> list) {
        infoList.clear();
        infoMap.clear();
        for (I info : list) {
            infoMap.put(info.getResource(), info);
            infoList.add(info);
        }
        logger.log(Level.INFO, "Loaded " + infoList.size() + " bit values");
    }

    protected record RecursivePair<R,I extends BitInfo<R>>(R resource, BitRegistryBuilder<R, I> builder) {}
}
