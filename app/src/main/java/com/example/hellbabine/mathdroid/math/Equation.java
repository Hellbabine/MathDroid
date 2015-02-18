package com.example.hellbabine.mathdroid.math;

import java.util.ArrayList;

import com.example.hellbabine.mathdroid.math.Element;
import com.example.hellbabine.mathdroid.math.Element.Type;

public class Equation 
{
	private ArrayList<Element> leftMember;
	private ArrayList<Element> rightMember;
	
	public Equation()
	{
		this.leftMember = new ArrayList<Element>();
		this.rightMember = new ArrayList<Element>();
	}
	
	public boolean validateString()
	{
		int parenthesis = 0; 
		//partie Gauche
		for (int i = 0; i < this.leftMember.size(); i++)
		{
			Element currentElement = this.leftMember.get(i);
			Element nextElement = null; 
			if(i+2 <= this.leftMember.size())
			{
				nextElement = this.leftMember.get(i + 1); 
			}
			
			//si l'�quation commence par un op�rateur, il est invalide (pas un plus, moins)
			if(i == 0  && !isPlusMinus(currentElement) && currentElement.getType() == Type.OPERATOR)
			{
				return false; 
			}
			if(nextElement != null)
			{
				//si l'�quation ressemble a : *), +). -), /), ^)
				if(currentElement.getType() == Type.OPERATOR && nextElement.getValue().equals(")"))
				{
					return false;
				}
				//si x34
				if(currentElement.hasVariable() && nextElement.getType() == Type.NUMBER)
				{
					return false; 
				}
				if(currentElement.getType() == Type.OPERATOR && nextElement.getType() == Type.OPERATOR)
				{
					if(isPlusMinus(currentElement) && !isPlusMinus(nextElement))
					{
						return false ;
					}
					if(i+2 <= this.leftMember.size())
					{
						Element thirdElement = this.leftMember.get(i + 2);
						if(thirdElement.getType() == Type.OPERATOR)
						{
							return false; 
						}
					}
				}
			}
			else
			{
				if(currentElement.getType() == Type.OPERATOR)
				{
					return false; 
				}
			}
			
			if(currentElement.getType() == Type.PARENTHESIS)
			{
				if(currentElement.getValue().equals("("))
				{
					parenthesis ++; 
				}
				else
				{
					parenthesis --; 
				}
				//Une parenthese ferme avant d'avoir ete ouverte
				if(parenthesis < 0)
				{
					return false; 
				}
			}
		}
		//Verifie parenthese partie gauche 
		if(parenthesis != 0)
		{
			return false;
		}
		//partie Droite
		/**
		for (int i = 0; i < this.rightMember.size(); i++)
		{
			Element currentElement = this.leftMember.get(i);
			//si l'�quation commence par un op�rateur, il est invalide
			if(i == 0  && currentElement.getType() == Type.OPERATOR)
			{
				return false; 
			}
		}
		**/
		return true; 
	}

public boolean isPlusMinus(Element value)
	{
		if(value.getValue().equals("+") || value.getValue().equals("-"))
		{
			return true;
		}
		return false;
	}

	public void CreateFromValidString(String equation)
	{
		boolean foundEqual = false;
		
		for (int current = 0; current < equation.length(); current++)
		{
			if (foundEqual == false)
			{
				//left side
				if (equation.charAt(current) == '=')
				{
					foundEqual = true;
				}
				else
				{
					int[] array = {current};
					ExtractElement(this.leftMember, equation, array);
					current = array[0];
				}
			}
			else
			{
				//right side
				int[] array = {current};
				ExtractElement(this.rightMember, equation, array);
				current = array[0];
			}
		}
	}
	
	public String SolveForX()
	{
		//REVOIR DETERMINER COTER VARIABLE
		int leftSideVariableCount = 1;
		int rightSideVariableCount = 0;
		
		if (leftSideVariableCount != 0 && rightSideVariableCount != 0)
		{
			return VariablesOnBothSides();
		}
		if (leftSideVariableCount == 0)
		{
			VariablesOnRightSide();
		}
		
		return VariablesOnLeftSide();
		
	}
	
	private void AdditionSubstraction()
	{
		for (int i = 0; i < this.leftMember.size(); i++)
		{
			Element currentElement = this.leftMember.get(i);
			if (currentElement.getType() == Type.NUMBER && currentElement.hasVariable() == false)
			{
				try
				{
					Element previousElement = this.leftMember.get(i-1);
					if (previousElement.getType() == Type.OPERATOR)
					{
						if (this.leftMember.size() <= i + 1)
						{
							//DERNIER ELEMENT, DONC, NOT AFFECTED BY ANOTHER OPERATOR, PROCEED
							RemoveAdditionSubstraction(previousElement, currentElement, i);
						}
						else
						{
							//VERIFY NEXT ELEMENT TO MAKE SURE IT'S NOT AFFECTED BY SOMETHING
							Element nextElement = this.leftMember.get(i+1);
							if (nextElement.getType() == Type.OPERATOR)
							{
								if (currentElement.getValue().equals("*") || currentElement.getValue().equals("/") || currentElement.getValue().equals("^"))
								{
									// Do nothing, priority of operation
								}
								else
								{
									RemoveAdditionSubstraction(previousElement, currentElement, i);
									--i;
								}
							}
							
						}
						
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					Element oppositeOperation = new Element("-", Type.OPERATOR, false);
					this.rightMember.add(oppositeOperation);
					this.leftMember.remove(i);
					this.rightMember.add(currentElement);
				}
			}
				
		}
	}
	
	
	private void MultiplyDivide()
	{
		for (int i = 0; i < this.leftMember.size(); i++)
		{
			Element currentElement = this.leftMember.get(i);
			if (currentElement.hasVariable())
			{
				if (Double.parseDouble(currentElement.getValue()) != 1)
				{
					this.rightMember.add(new Element("/", Type.OPERATOR, false));
					this.rightMember.add(new Element(currentElement.getValue(), Type.NUMBER, false));
					this.leftMember.remove(i);
					this.leftMember.add(new Element("1", Type.NUMBER, true));
				}
			}
		}
	}

	
	private String VariablesOnLeftSide()
	{
		ReduceMultiplyDivide(this.leftMember); //R�duit les * / du cot� des variables
		ReduceAdditionSubstraction(this.leftMember);
		AdditionSubstraction(); // switch les add sous de lautre cot�
		
		ReduceAdditionSubstraction(this.rightMember); //r�duit les additions soustraction des const
		
		MultiplyDivide(); // switch les * / de l'autre cot�
		
		ReduceMultiplyDivide(this.rightMember);
		
		return BuildAnswer(this.rightMember);
		//return ReduceMultiplyDivide(this.rightMember);
	}
	
	private void RemoveAdditionSubstraction(Element previousElement, Element currentElement, int i)
	{
		if (previousElement.getValue().equals("+"))
		{
			this.leftMember.remove(i-1);
			Element oppositeOperation = new Element("-", Type.OPERATOR, false);
			this.rightMember.add(oppositeOperation);
			i--;
			this.leftMember.remove(i);
			this.rightMember.add(currentElement);
			i--;
		}
		if (previousElement.getValue().equals("-"))
		{
			this.leftMember.remove(i-1);
			Element oppositeOperation = new Element("+", Type.OPERATOR, false);
			this.rightMember.add(oppositeOperation);
			i--;
			this.leftMember.remove(i);
			this.rightMember.add(currentElement);
			i--;
		}
	}
	
	private void RemoveMultiplicationDivision(Element previousElement, Element currentElement, int i)
	{
		if (previousElement.getValue().equals("*"))
		{
			this.leftMember.remove(i-1);
			Element oppositeOperation = new Element("/", Type.OPERATOR, false);
			this.rightMember.add(oppositeOperation);
			i--;
			this.leftMember.remove(i);
			this.rightMember.add(currentElement);
			i--;
		}
		if (previousElement.getValue().equals("/"))
		{
			this.leftMember.remove(i-1);
			Element oppositeOperation = new Element("*", Type.OPERATOR, false);
			this.rightMember.add(oppositeOperation);
			i--;
			this.leftMember.remove(i);
			this.rightMember.add(currentElement);
			i--;
		}
	}

	private String VariablesOnRightSide()
	{
		return null;
	}
	
	private String VariablesOnBothSides()
	{
		return null;
	}
	
	private String BuildAnswer(ArrayList<Element> side)
	{
		String answer = "";
		
		for (int i = 0; i < side.size(); i++)
		{
			Element currentElement = side.get(i);
			answer += " " + currentElement.getValue();
		}
		
		return answer;
	}

	private void ReduceAdditionSubstraction(ArrayList<Element> side)
	{
		for (int i = 0; i < side.size(); i++)
		{
			Element currentElement = side.get(i);
			if (currentElement.getType() == Type.NUMBER)
			{	
				try
				{
					Element supposedOperator = side.get(i-1);
					Element supposedNumber = side.get(i-2);
					
					if (supposedOperator.getType() == Type.OPERATOR)
					{
						if (supposedOperator.getValue().equals("-") && supposedNumber.getType() == Type.NUMBER)
						{
							//Soustraction
							if (supposedNumber.hasVariable() == false && currentElement.hasVariable() == false)
							{
								double result = Double.parseDouble(supposedNumber.getValue()) - Double.parseDouble(currentElement.getValue());
								side.remove(i);
								--i;
								side.remove(i);
								--i;
								side.remove(i);
								
								side.add(i, new Element(String.valueOf(result), Type.NUMBER, false));
							}
							else if (supposedNumber.hasVariable() && currentElement.hasVariable())
							{
								double result = Double.parseDouble(supposedNumber.getValue()) - Double.parseDouble(currentElement.getValue());
								side.remove(i);
								--i;
								side.remove(i);
								--i;
								side.remove(i);
								
								side.add(i, new Element(String.valueOf(result), Type.NUMBER, true));
							}

							else
							{
								//Okay, si je peut pas faire l'op�ration, je dois glitcher le negatif du nombre comme si c'�tait le premier de la liste
								String value = "-" + currentElement.getValue();
								Element newNegative = new Element(value, Type.NUMBER, currentElement.hasVariable());
								side.add(0, newNegative);
								side.remove(1);
								side.remove(1);
							}
							
							
							
						}
						
						if (supposedOperator.getValue().equals("+"))
						{
							//Addition
							if (supposedNumber.hasVariable() == false && currentElement.hasVariable() == false)
							{
								double result = Double.parseDouble(currentElement.getValue()) + Double.parseDouble(supposedNumber.getValue());
								side.remove(i);
								--i;
								side.remove(i);
								--i;
								side.remove(i);
								
								side.add(i, new Element(String.valueOf(result), Type.NUMBER, false));
							}
							if (supposedNumber.hasVariable() && currentElement.hasVariable())
							{
								double result = Double.parseDouble(currentElement.getValue()) + Double.parseDouble(supposedNumber.getValue());
								side.remove(i);
								--i;
								side.remove(i);
								--i;
								side.remove(i);
								
								side.add(i, new Element(String.valueOf(result), Type.NUMBER, true));
							}
						}
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					//First number , what to do if negative
					if (i != 0 && currentElement.getType() == Type.NUMBER)
					{
						Element negative = side.get(i-1);
						if (negative.getValue().equals("-"))
						{
							String value = "-" + currentElement.getValue();
							Element newNegative = new Element(value, Type.NUMBER, currentElement.hasVariable());
							side.add(0, newNegative);
							side.remove(1);
							side.remove(1);
						}
					}
				}
			}
		}

	}
	
	private void ReduceMultiplyDivide(ArrayList<Element> side)
	{	
		
		for (int i = 0; i < side.size(); i++)
		{
			Element currentElement = side.get(i);
			if (currentElement.getType() == Type.NUMBER)
			{	
				boolean variableInFirstElement = currentElement.hasVariable();
				try
				{
					Element nextElement = side.get(i+1);
					if (nextElement.getType() == Type.OPERATOR)
					{
						if (nextElement.getValue().equals("*"))
						{
							Element multiplierElement = side.get(i+2);
							if (multiplierElement.getValue().equals("-"))
							{
								multiplierElement = side.get(i+3);
								
								double multiplier = Double.parseDouble(multiplierElement.getValue());
								double result = multiplier * Double.parseDouble(currentElement.getValue());
								
								boolean variableInSecondElement = multiplierElement.hasVariable();
								//TODO : Need to add a case if both are true
								boolean resultHasVariable = variableInFirstElement || variableInSecondElement;
								
								side.remove(i);
								side.remove(i);
								side.remove(i);
								side.remove(i);
								
								side.add(i, new Element(Double.toString(result), Type.NUMBER, resultHasVariable));
								side.add(i, new Element("-", Type.OPERATOR, false));
							}
							else
							{
								if (multiplierElement.getType() == Type.NUMBER)
								{
									double multiplier = Double.parseDouble(multiplierElement.getValue());
									double result = multiplier * Double.parseDouble(currentElement.getValue());
									
									boolean variableInSecondElement = multiplierElement.hasVariable();
									
									//TODO : Need to add a case if both are true
									boolean resultHasVariable = variableInFirstElement || variableInSecondElement;
									
									side.remove(i);
									side.remove(i);
									side.remove(i);
									
									side.add(i, new Element(Double.toString(result), Type.NUMBER, resultHasVariable));
								
								}
							}
							
						}
						
						
						if (nextElement.getValue().equals("/"))
						{
							//TODO : Revoir la logique des variables, ne fonctionne pas
							
							Element diviseurElement = side.get(i+2);
							if (diviseurElement.getValue().equals("-"))
							{
								diviseurElement = side.get(i+3);
								
								double diviseur = Double.parseDouble(diviseurElement.getValue());
								double result = Double.parseDouble(currentElement.getValue()) / diviseur;
								
								boolean variableInSecondElement = diviseurElement.hasVariable();
								boolean resultHasVariable = DivisionWithVariable(variableInFirstElement, variableInSecondElement);
								
								side.remove(i);
								side.remove(i);
								side.remove(i);
								side.remove(i);
								
								side.add(i, new Element(Double.toString(result), Type.NUMBER, resultHasVariable));
								side.add(i, new Element("-", Type.OPERATOR, false));
							}
							else
							{
								if (diviseurElement.getType() == Type.NUMBER)
								{
									double diviseur = Double.parseDouble(diviseurElement.getValue());
									double result = Double.parseDouble(currentElement.getValue()) / diviseur;
									
									boolean variableInSecondElement = diviseurElement.hasVariable();
									
									boolean resultHasVariable = DivisionWithVariable(variableInFirstElement, variableInSecondElement);
									
									side.remove(i);
									side.remove(i);
									side.remove(i);
									
									side.add(i, new Element(Double.toString(result), Type.NUMBER, resultHasVariable));
								
								}
							}
						}
					}
				}
				catch (IndexOutOfBoundsException e)
				{
					//last element, do nothing
				}
			}
		
		}
			
	}
	
	private boolean DivisionWithVariable(boolean top, boolean bottom)
	{
		if (top == true && bottom == false)
		{
			return true;
		}
		if (top == true && bottom == true)
		{
			return false;
		}
		if (top == false && bottom == false)
		{
			return false;
		}
		if (top == false && bottom == true)
		{
			//TODO : X should stay under... what do ?
			return true;
		}
		return false;
	}

	private void ExtractElement(ArrayList<Element> currentSide, String equation, int[] position)
	{
		int initialPosition = position[0];
		
		//NUMBER
		if (Character.isDigit(equation.charAt(position[0])))
		{
			try
			{
				while (Character.isDigit(equation.charAt(position[0] + 1)))
				{
					position[0]++;
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				//do not increment, max lenght reached
			}
			
			
			String value = equation.substring(initialPosition, position[0] + 1);
			Element.Type type = Type.NUMBER;
			
			currentSide.add(new Element(value, type, false));
			return;
		}
		//Variable
		else if (equation.charAt(initialPosition) == 'x' || equation.charAt(initialPosition) == 'X')
		{
			try
			{
				Element lastElementAdded = currentSide.get(currentSide.size()-1);
				if (lastElementAdded.getType() == Type.NUMBER)
				{
					lastElementAdded.setVariable(true);
				}
				else
				{
					currentSide.add(new Element("1", Type.NUMBER, true));
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				currentSide.add(new Element("1", Type.NUMBER, true));
				return;
			}
		}
		//Parenthesis
		else if (equation.charAt(initialPosition) == '(' || equation.charAt(initialPosition) == ')')
		{
			String value = String.valueOf(equation.charAt(initialPosition));
			Element.Type type = Type.PARENTHESIS;
			
			currentSide.add(new Element(value, type, false));
			return;
		}
		//Operators
		else
		{
			String value = String.valueOf(equation.charAt(initialPosition));
			Element.Type type = Type.OPERATOR;
			
			currentSide.add(new Element(value, type, false));
			return;
		}
	}
	
	public void ClearEquation()
	{
		this.leftMember.clear();
		this.rightMember.clear();
	}
	
	public ArrayList<Element> getLeft()
	{
		return this.leftMember;
	}
	
	public ArrayList<Element> getRight()
	{
		return this.rightMember;
	}
}
