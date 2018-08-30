package com.jasonfoxsoftware.gameoflifeanddeath.common;

import com.sun.org.apache.xpath.internal.operations.Neg;

public class InvalidCoordinateException extends RuntimeException
{

    public InvalidCoordinateException()
    {
        super();
    }

    public InvalidCoordinateException(String message)
    {
        super(message);
    }
}
