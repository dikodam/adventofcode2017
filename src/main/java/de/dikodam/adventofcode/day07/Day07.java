package de.dikodam.adventofcode.day07;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Tools;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day07 extends AbstractDay {

    private Map<String, Node> nodes;
    private List<Edge> edges;

    public static void main(String[] args) {
        Day07 day07 = new Day07();
        day07.task1();
        day07.task2();
    }

    public Day07() {
        init();
    }

    private void init() {
        List<String> input = Tools.getInput(this.getClass().getSimpleName());
        nodes = getNodes(input);
        edges = getEdges(input);
        buildGraph();
    }

    @Override
    public void task1() {
        Node root = findRootNode();
        System.out.println("Task 1: root node is " + root.getName());
    }

    private List<Edge> getEdges(List<String> input) {
        return input.stream()
            .filter(line -> line.contains("->"))
            .flatMap(line -> extractEdges(line).stream())
            .collect(toList());
    }

    private Map<String, Node> getNodes(List<String> input) {
        return input.stream()
            .map(this::extractNode)
            .collect(Collectors.toMap(Node::getName, Function.identity()));
    }

    private Node findRootNode() {
        // randomly pick a node
        Node iterationNode = edges.get(0).getAncestor();
        Optional<Node> ancestor = getAncestorOf(iterationNode);
        while (ancestor.isPresent()) {
            iterationNode = ancestor.get();
            ancestor = getAncestorOf(iterationNode);
        }
        return iterationNode;
    }

    private Optional<Node> getAncestorOf(Node node) {
        return edges.stream()
            .filter(edge -> node.equals(edge.getChild()))
            .findFirst()
            .map(Edge::getAncestor);
    }

    private List<Edge> extractEdges(String line) {
        String[] splitLine = line.split("->");
        Node parent = nodes.get(extractNode(splitLine[0]).getName());
        String[] targetsAsStrings = splitLine[1].split(" ");
        return Arrays.stream(targetsAsStrings)
            .map(s -> s.replace(',', ' ').trim())
            .filter(s -> !s.isEmpty())
            .map(nodes::get)
            .map(targetNode -> new Edge(parent, targetNode))
            .collect(toList());
    }

    private Node extractNode(String line) {
        String[] splitLine = line.split(" ");
        String name = splitLine[0];
        int weight = Integer.parseInt(splitLine[1].replaceAll("[()]", "").trim());
        return new Node(name, weight);
    }

    private void buildGraph() {
        for (Edge edge : edges) {
            Node ancestor = edge.getAncestor();
            Node child = edge.getChild();
            ancestor.addChild(child);
            child.setAncestor(ancestor);
        }
    }

    @Override
    public void task2() {
        Node rootNode = findRootNode();

    }
}
