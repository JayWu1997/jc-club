package com.jingdianjichi.circle.common.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jay
 * @since 2025/1/23 下午5:57
 */
public class TreeUtil {

    @Data
    public static abstract class Node<K, V> {
        // 子节点
        private List<V> children;
        // 节点id
        private K nodeId;
        // 父节点id
        private K pNodeId;
    }

    /**
     * 用 node 集合构建树形结构，可能有多棵树，返回每棵树的根节点集合
     * @param nodes 节点集合
     * @param rootPId 根节点 id 值
     * @return 根节点集合
     * @param <K> 节点 id 的类型
     * @param <V> 节点值的类型
     */
    public static <K, V extends Node<K, V>> List<V> buildTrees(List<V> nodes, K rootPId) {
        // 筛选出所有父节点的子节点集合，pid map childrenList
        Map<K, List<V>> pidMapChildrenList = nodes.stream().collect(Collectors.groupingBy(Node::getPNodeId));
        // 为每个节点设置子节点集合
        nodes.forEach(node -> node.setChildren(pidMapChildrenList.get(node.getNodeId())));
        // 返回所有根节
        return pidMapChildrenList.get(rootPId);
    }

}
