package de.dikodam.adventofcode.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Node {
    private final String name;
    private final int weight;
    private Node ancestor;
    private List<Node> children;

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
        ancestor = null;
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getDeepWeight() {
        return weight + getChildrenWeight();
    }

    public int getChildrenWeight() {
        return children.stream()
            .mapToInt(Node::getDeepWeight)
            .sum();
    }

    public List<Integer> getChildrenWeights() {
        return children.stream()
            .map(Node::getDeepWeight)
            .collect(toList());
    }

    public Node getAncestor() {
        return ancestor;
    }

    public void setAncestor(Node ancestor) {
        this.ancestor = ancestor;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public boolean hasUnbalancedSubtree() {
        long distinctWeights = getChildrenWeights()
            .stream()
            .distinct()
            .count();
        return distinctWeights > 1;
    }

    public Optional<Node> getUnbalancedSubNode() {
        Map<Integer, List<Node>> weightToNodesMap = children.stream()
            .collect(groupingBy(Node::getDeepWeight));

        return weightToNodesMap.entrySet().stream()
            .map(Map.Entry::getValue)
            .filter(nodeList -> nodeList.size() == 1)
            .flatMap(List::stream)
            .findFirst();
    }

    @Override
    public String toString() {
        return String.format("%s: (%d|%d)", name, weight, getDeepWeight());
    }

    public void printTree() {
        StringBuilder sb = new StringBuilder("root: ")
            .append(this)
            .append("\n")
            .append("children: ")
            .append("\n");
        children.forEach(child -> sb.append(child).append(" || "));
        System.out.println(sb.toString());

    }
}
