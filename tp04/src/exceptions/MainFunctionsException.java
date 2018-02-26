package exceptions;

/** Main Functions Exception tackles errors during Loading, Editing, Saving, ... */

public class 				MainFunctionsException
	extends 				Exception
{
	static final long serialVersionUID = 30122017L;

	public 					MainFunctionsException(String function, String msg)
	{
		super("An error occured during " + function + ": " + msg);
	}
}
