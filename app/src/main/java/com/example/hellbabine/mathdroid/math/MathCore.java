package com.example.hellbabine.mathdroid.math;

public class MathCore 
{
	private Equation equation;
	public MathCore()
	{
		this.equation = new Equation();
	}
	
	public String SendEquation(String strEquation)
	{
		String returnValue = "";
		//ValidateEquation
		
		
		//build structure
		this.equation = new Equation();
		this.equation.CreateFromValidString(strEquation);
		if(!this.equation.validateString())
		{
			return "Erreur";
		}
		
		//solve
		returnValue = this.equation.SolveForX();
		
		//clear and return
		this.equation.ClearEquation();
		return returnValue;
	}
	

	public static void main(String[] args)
	{
		//testing purposes
	
		
		MathCore core = new MathCore();
		/*
		System.out.println("15+x=20");
		System.out.println("Should be : 5");
		System.out.println("Is : " + core.SendEquation("15+x=20"));
		System.out.println();
		
		System.out.println("-5+x=5");
		System.out.println("Should be : 10");
		System.out.println("Is : " + core.SendEquation("-5+x=5"));
		System.out.println();
		
		System.out.println("x+10=20");
		System.out.println("Should be : 10");
		System.out.println("Is : " + core.SendEquation("x+10=20"));
		System.out.println();
		
		System.out.println("x-10=5");
		System.out.println("Should be : 15");
		System.out.println("Is : " + core.SendEquation("x-10=5"));
		System.out.println();
		
		System.out.println("5+10+x=20");
		System.out.println("Should be : 5");
		System.out.println("Is : " + core.SendEquation("5+10+x=20"));
		System.out.println();
		
		System.out.println("5+x+5=20");
		System.out.println("Should be : 10");
		System.out.println("Is : " + core.SendEquation("5+x+5=20"));
		System.out.println();
		
		System.out.println("5+x-10=10");
		System.out.println("Should be : 15");
		System.out.println("Is : " + core.SendEquation("5+x-10=10"));
		System.out.println();
		
		System.out.println("5-10+x=5");
		System.out.println("Should be : 10");
		System.out.println("Is : " + core.SendEquation("5-10+x=5"));
		System.out.println();
		
		System.out.println("x+3*2=8");
		System.out.println("Should be : 2");
		System.out.println("Is : " + core.SendEquation("x+3*2=8"));
		System.out.println();
		
		System.out.println("x+3*2+4=15");
		System.out.println("Should be : 5");
		System.out.println("Is : " + core.SendEquation("x+3*2+4=15"));
		System.out.println();
		
		System.out.println("x-2*5+8-3=20");
		System.out.println("Should be : 25");
		System.out.println("Is : " + core.SendEquation("x-2*5+8-3=20"));
		System.out.println();
		
		System.out.println("7-3*2+x-5=25");
		System.out.println("Should be : 29");
		System.out.println("Is : " + core.SendEquation("7-3*2+x-5=25"));
		System.out.println();
		
		System.out.println("x+8*-3=120");
		System.out.println("Should be : 144");
		System.out.println("Is : " + core.SendEquation("x+8*-3=120"));
		System.out.println();

		System.out.println("-8*3+x=50");
		System.out.println("Should be : 74");
		System.out.println("Is : " + core.SendEquation("-8*3+x=50"));
		System.out.println();
		
		
		System.out.println("8*x=16");
		System.out.println("Should be : 2");
		System.out.println("Is : " + core.SendEquation("8*x=16"));
		System.out.println();
		**/
		System.out.println(")3+2=1");
		System.out.println("Should be : False");
		System.out.println("Is : " + core.SendEquation(")3+2=1"));
		System.out.println();
		
		System.out.println("(32+3)(=5");
		System.out.println("Should be : False");
		System.out.println("Is : " + core.SendEquation("(32+3)(=5"));
		System.out.println();
		
		System.out.println("(32+3))=5");
		System.out.println("Should be : False");
		System.out.println("Is : " + core.SendEquation("(32+3))=5"));
		System.out.println();

		System.out.println("32+x=5");
		System.out.println("Should be : true");
		System.out.println("Is : " + core.SendEquation("32+x=5"));
		System.out.println();
		
		System.out.println("32+5*=25");
		System.out.println("Should be : False");
		System.out.println("Is : " + core.SendEquation("32+5*=25"));
		System.out.println();
		
		System.out.println("32+x23=25");
		System.out.println("Should be : False");
		System.out.println("Is : " + core.SendEquation("32+x23=25"));
		System.out.println();
		
		System.out.println("-32+-20x=5");
		System.out.println("Should be : True");
		System.out.println("Is : " + core.SendEquation("-32+-20x=5"));
		System.out.println();
		
		System.out.println("-32+-+20x=5");
		System.out.println("Should be : False");
		System.out.println("Is : " + core.SendEquation("-32+-+20x=5"));
		System.out.println();
		
		System.out.println("50-2x^9=52/96");
		System.out.println("Should be : True");
		System.out.println("Is : " + core.SendEquation("50-2x^9=52/96"));
		System.out.println();
		/**
		System.out.println("35*(-32x)/5+-25=10");
		System.out.println("Should be : True");
		System.out.println("Is : " + core.SendEquation("35*(-32x)/5+-25=10"));
		System.out.println();
		**/


	}
}
