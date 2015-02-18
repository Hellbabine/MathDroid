package com.example.hellbabine.mathdroid.math;

public class Element 
{
	private String value;
	private Type type;
	private boolean hasVariable;
	
	public Element(String value, Type type, boolean variable)
	{
		this.value = value;
		this.type = type;
		this.hasVariable = variable;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public Type getType()
	{
		return this.type;
	}
	
	public Boolean setVariable(boolean variable)
	{
		return this.hasVariable = variable;
	}
	
	public Boolean hasVariable()
	{
		return this.hasVariable;
	}
	
	public enum Type
	{
		NUMBER, VARIABLE, OPERATOR, PARENTHESIS
	}
}
