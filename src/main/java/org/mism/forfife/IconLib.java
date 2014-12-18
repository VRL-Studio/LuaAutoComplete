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

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconLib {

	static IconLib instance = new IconLib();
	private Icon memberFunctionIcon, functionIcon, variableIcon, libraryIcon;

	private IconLib() {
		try {
			memberFunctionIcon = new ImageIcon(IconLib.class.getClassLoader()
					.getResource("org/mism/forfife/icons/memberFunction.gif"));
			functionIcon = new ImageIcon(IconLib.class.getClassLoader()
					.getResource("org/mism/forfife/icons/function.gif"));
			variableIcon = new ImageIcon(IconLib.class.getClassLoader()
					.getResource("org/mism/forfife/icons/variable.gif"));
			libraryIcon = new ImageIcon(IconLib.class.getClassLoader()
					.getResource("org/mism/forfife/icons/library.gif"));
		} catch (Exception e) {
            Logging.error("Could not load icons.", e);
		}
	}

	public Icon getFunctionIcon() {
		return functionIcon;
	}

	public Icon getMemberFunctionIcon() {
		return memberFunctionIcon;
	}

	public Icon getLibraryIcon() {
		return libraryIcon;
	}

	public Icon getVariableIcon() {
		return variableIcon;
	}

	public static IconLib instance() {
		return instance;
	}

}