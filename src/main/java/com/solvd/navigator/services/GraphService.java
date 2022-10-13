package com.solvd.navigator.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GraphService {

    public static class Graph {
        private Set<NodeService.Node> nodes = new HashSet<>();

        public void addNode(NodeService.Node nodeA) {
            nodes.add(nodeA);
        }

        public Set<NodeService.Node> getNodes() {
            return nodes;
        }

        public void setNodes(Set<NodeService.Node> nodes) {
            this.nodes = nodes;
        }

        private void calculateMinimumDistance(NodeService.Node evaluationNode,
                                                     Double edgeWeigh, NodeService.Node sourceNode) {
            Double sourceDistance = sourceNode.getDistance();
            if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
                evaluationNode.setDistance(sourceDistance + edgeWeigh);
                LinkedList<NodeService.Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
                shortestPath.add(sourceNode);
                evaluationNode.setShortestPath(shortestPath);
            }
        }
    }

    public static Graph calculateShortestPathFromSource(Graph graph, NodeService.Node source) {
        source.setDistance(0.0);

        Set<NodeService.Node> settledNodes = new HashSet<>();
        Set<NodeService.Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            NodeService.Node currentNode = NodeService.getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<NodeService.Node, Double> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                NodeService.Node adjacentNode = adjacencyPair.getKey();
                Double edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    graph.calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

}
