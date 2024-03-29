package moe.sylvi.bitexchange.bit.research;

import moe.sylvi.bitexchange.bit.info.BitInfoResearchable;
import moe.sylvi.bitexchange.bit.registry.BitRegistry;
import net.minecraft.entity.player.PlayerEntity;
import java.util.ArrayList;
import java.util.List;

public interface PlayerBitKnowledge<T, R extends BitInfoResearchable<T>> extends BitKnowledge<T> {
    PlayerEntity getPlayer();
    BitRegistry<T, R> getBitRegistry();

    @Override
    default long getKnowledge(T resource) {
        return getKnowledgeMap().getOrDefault(resource, 0L);
    }

    @Override
    default long addKnowledge(T resource, long count) {
        var knowledgeMap = getKnowledgeMap();
        var current = knowledgeMap.getOrDefault(resource, 0L);
        var info = getBitRegistry().get(resource);
        if (info != null) {
            var added = Math.min(count, info.getResearch() - current);
            knowledgeMap.put(resource, current + added);
            setKnowledgeMap(knowledgeMap);
            return added;
        }
        return 0;
    }

    @Override
    default void removeKnowledge(T resource) {
        var knowledgeMap = getKnowledgeMap();
        knowledgeMap.remove(resource);
        setKnowledgeMap(knowledgeMap);
    }

    @Override
    default boolean learn(T resource) {
        var knowledgeMap = getKnowledgeMap();
        var info = getBitRegistry().get(resource);
        if (info != null && knowledgeMap.getOrDefault(resource, 0L) < info.getResearch()) {
            knowledgeMap.put(resource, info.getResearch());
            setKnowledgeMap(knowledgeMap);
            return true;
        }
        return false;
    }

    @Override
    default boolean hasLearned(T resource) {
        var knowledgeMap = getKnowledgeMap();
        var info = getBitRegistry().get(resource);
        if (info != null) {
            return knowledgeMap.getOrDefault(resource, 0L) >= info.getResearch();
        }
        return false;
    }

    @Override
    default boolean canLearn(T resource) {
        var info = getBitRegistry().get(resource);
        if (info != null) {
            if (!info.isResearchable()) {
                return false;
            }
            var failedAny = false;
            for (var requirement : info.getResearchRequirements()) {
                if (!requirement.isMet(getPlayer())) {
                    failedAny = true;
                    break;
                }
            }
            return !failedAny;
        }
        return false;
    }

    @Override
    default List<T> getAllLearned() {
        var knowledgeMap = getKnowledgeMap();
        var result = new ArrayList<T>();
        for (var resource : getBitRegistry().getResourceRegistry()) {
            var info = getBitRegistry().get(resource);
            if (info != null && knowledgeMap.getOrDefault(resource, 0L) >= info.getResearch()) {
                result.add(resource);
            }
        }
        return result;
    }
}
