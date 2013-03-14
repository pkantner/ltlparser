package de.prob.ltl;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.prob.parserbase.UnparsedParserBase;
import de.prob.prolog.term.PrologTerm;


public class CompatibilityTest extends AbstractLtlParserTest {

	private static UnparsedParserBase parserBase;
	private static de.be4.ltl.core.parser.LtlParser oldParser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		parserBase = new UnparsedParserBase("expr", "pred", "trans");
		oldParser = new de.be4.ltl.core.parser.LtlParser(parserBase);
	}

	// Helper
	protected String parseOld(String input) throws Exception {
		PrologTerm term = oldParser.generatePrologTerm(input, "current");
		return term.toString();
	}

	@Test
	public void testConstants() throws Exception {
		for (String input : new String[] {
				" true",
				"false ",
				"sink",
				"deadlock", "current" }) {
			assertEquals(parseOld(input), parse(input));
		}
	}

}