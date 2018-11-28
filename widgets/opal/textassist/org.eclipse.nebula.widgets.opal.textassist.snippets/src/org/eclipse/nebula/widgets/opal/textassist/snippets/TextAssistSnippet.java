/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Laurent CARON (laurent.caron@gmail.com)
 *******************************************************************************/
package org.eclipse.nebula.widgets.opal.textassist.snippets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.opal.textassist.TextAssist;
import org.eclipse.nebula.widgets.opal.textassist.TextAssistContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A simple snippet for the TextAssist Widget
 */
public class TextAssistSnippet {
	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());

		final TextAssistContentProvider contentProvider = new TextAssistContentProvider() {

			private final String[] EUROZONE = new String[] { "Austria", "Belgium", "Cyprus", "Estonia", "Finland", "France", "Germany", "Greece", "Ireland", "Italy", "Luxembourg", "Malta", "Netherlands", "Portugal", "Slovakia", "Slovenia", "Spain" };

			@Override
			public List<String> getContent(final String entry) {
				final List<String> returnedList = new ArrayList<String>();

				for (final String country : EUROZONE) {
					if (country.toLowerCase().startsWith(entry.toLowerCase())) {
						returnedList.add(country);
					}
				}

				return returnedList;
			}
		};

		final Label lblTextAssist = new Label(shell, SWT.NONE);
		lblTextAssist.setText("Text field with text assist:");

		final TextAssist textAssist = new TextAssist(shell, SWT.SINGLE | SWT.BORDER, contentProvider);
		textAssist.setLayoutData(new GridData(150, SWT.DEFAULT));

		new Label(shell, SWT.NONE);
		final Label lblSimpleText = new Label(shell, SWT.NONE);
		lblSimpleText.setText("Simple Text field:");

		final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(150, SWT.DEFAULT));

		new Label(shell, SWT.NONE);
		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Force focus on Text Assist Field");
		button.addListener(SWT.Selection, (e) -> textAssist.setFocus());

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
