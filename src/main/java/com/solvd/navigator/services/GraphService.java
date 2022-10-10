package com.solvd.navigator.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GraphService {

    private Set<NodeService> nodes = new HashSet<>();

    public void addNode(NodeService nodeA) {
        nodes.add(nodeA);
    }

    public Set<NodeService> getNodes() {
        return nodes;
    }

    public void setNodes(Set<NodeService> nodes) {
        this.nodes = nodes;
    }

    private static void calculateMinimumDistance(NodeService evaluationNode,
                                                 Integer edgeWeigh, NodeService sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<NodeService> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static GraphService calculateShortestPathFromSource(GraphService graph, NodeService source) {
        source.setDistance(0);

        Set<NodeService> settledNodes = new HashSet<>();
        Set<NodeService> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            NodeService currentNode = NodeService.getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<NodeService, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                NodeService adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }



}
