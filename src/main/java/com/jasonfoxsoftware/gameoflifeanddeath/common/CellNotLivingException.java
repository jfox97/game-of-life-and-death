package com.jasonfoxsoftware.gameoflifeanddeath.common;

public class CellNotLivingException extends RuntimeException
{

    public CellNotLivingException()
    {
        super();
    }

    public CellNotLivingException(String message)
    {
        super(message);
    }
}
