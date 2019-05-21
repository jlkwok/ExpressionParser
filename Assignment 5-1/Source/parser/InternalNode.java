package edu.cwru.jfh86_jlk187.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public final class InternalNode implements Node
{
    private final List<Node> nodeChildren;
    private String toStringResult;
    private List<Token> toListResult = new ArrayList<>();

    private InternalNode(List<Node> newChildren)
    {
        this.nodeChildren = Collections.unmodifiableList(new ArrayList<>(newChildren));
    }

    public static InternalNode build(List<Node> newChildren)
    {
        return new InternalNode(Objects.requireNonNull(newChildren, "Children of internal node were not specified, operation canceled"));
    }

    // returns copy of children -> new ArrayList()
    public final List<Node> getChildren()
    {
        return new ArrayList<>(this.nodeChildren);
    }

    // only computes list from scratch first time
    // else will just return the list 
    public final List<Token> toList()
    {
    	if (toListResult.isEmpty())
    	{
    		for (Node n : this.nodeChildren)
    			toListResult.addAll(n.toList());
    	}
    	return toListResult;
    }

    public final boolean isFruitful(){ return !this.nodeChildren.isEmpty(); }

    public final String toString() 
    {
    	if (toStringResult == null)
    		toStringResult = this.nodeChildren.stream().map(Object::toString).collect(Collectors.joining(",", "[", "]"));
    	return toStringResult;
    }

    public static class Builder
    {
        private List<Node> children = new ArrayList<>();

        public void addChild(Node node)
        {
            children.add(Objects.requireNonNull(node, "Child node was not specified, operation canceled"));
        }

        public Builder simplify()
        {
            children.removeIf(n -> (!n.isFruitful()));

            //if (children.get(0).firstChild().isSingleLeafParent() || children.get(0).isStartedByOperator())
            if((children.size() == 1 && children.get(0).isFruitful()) || children.get(0).isStartedByOperator())
            {
                children = children.get(0).getChildren();
            }

            return this;
        }

        public InternalNode build()
        {
        	System.out.println("build, children: " + this.simplify().children);
            return InternalNode.build(this.simplify().children);
        }
    }

	public boolean isOperator() {
		return false;
	}

	public boolean isStartedByOperator() { return (this.getChildren().get(0).isOperator()); }

	public Optional<Node> firstChild()
    {
		Optional<Node> child = Optional.empty();

		if (this.isFruitful()) 
			child = Optional.of(this.nodeChildren.get(0));

		return child;
	}

	public boolean isSingleLeafParent() { return (this.nodeChildren.size() == 1 && this.nodeChildren.get(0).getChildren().isEmpty());}
}
