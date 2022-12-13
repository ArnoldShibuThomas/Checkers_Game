package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import core.*;

@RunWith(Suite.class)
@SuiteClasses({ CheckerLogicTest.class, CheckersComputerPlayerTest.class})
public class AllTestsSuite {

}
