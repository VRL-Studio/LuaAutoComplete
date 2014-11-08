/*
 * Copyright (c) 2014, Goethe University, Goethe Center for Scientific Computing (GCSC), gcsc.uni-frankfurt.de
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.mism.forfife;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;
import org.mism.forfife.lua.LuaLexer;
import org.mism.forfife.lua.LuaParser;
/**
 * Register this class with org.fife.ui.rsyntaxtextarea.folding.FoldParserManager.get() singleton instance.
 * 
 * @author tr1nergy
 *
 */
public class LuaFoldParser implements FoldParser {

	@Override
	public List<Fold> getFolds(RSyntaxTextArea textArea) {
		try {
			
			ANTLRInputStream str = new ANTLRInputStream(new StringReader(
					textArea.getText()));
			Lexer lx = new LuaLexer(str);
			CommonTokenStream tokStr = new CommonTokenStream(lx);
			LuaParser parser = new LuaParser(tokStr);
			LuaFoldsVisitor lfv = new LuaFoldsVisitor(textArea);
			lfv.visit(parser.chunk());
			// System.out.println("Found " + lfv.getFolds().size() + " folds.");
			return lfv.getFolds();
		} catch (Exception e) {
			// System.out.println("No folds due to exception.");
			e.printStackTrace();
			return new ArrayList<Fold>();
		}
	}

}