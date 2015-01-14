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
package org.mism.forfife.visitors;

import org.mism.forfife.Logging;
import org.mism.forfife.LuaParseTreeUtil;
import org.mism.forfife.lua.LuaParser.StatContext;

public class FunctionVisitor extends LuaCompletionVisitor {

	static String FUNCTION = "function";

	@Override
	public Void visitStat(StatContext ctx) {
		if (isFunctionCtx(ctx)) {
			String functionName = funcName(ctx);
			if (functionName.contains(":")) {
				String className = functionName.substring(0,
						functionName.indexOf(":"));
				String memberFuncName = functionName.substring(functionName
						.indexOf(":") + 1);
				Logging.debug("Class: " + className + " append member "
						+ memberFuncName);
			}

		}
		return super.visitStat(ctx);
	}

	boolean isFunctionCtx(StatContext ctx) {
		String start = ctx.getStart().getText();
		return FUNCTION.equals(start);
	}

	StatContext getParentFunctionCtx(StatContext ctx) {
		StatContext parent = ctx;
		while ((parent = LuaParseTreeUtil.getParentStatContext(parent)) != null) {
			if (isFunctionCtx(parent))
				return parent;
		}
		return null;
	}

	String funcName(StatContext ctx) {
		assert isFunctionCtx(ctx);
		return ctx.getChild(1).getText();
	}
}
