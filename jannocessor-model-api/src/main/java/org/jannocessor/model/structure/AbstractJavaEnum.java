/**
 * Copyright 2011 Nikolche Mihajlovski
 *
 * This file is part of JAnnocessor.
 *
 * JAnnocessor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAnnocessor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAnnocessor.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jannocessor.model.structure;

import org.jannocessor.annotation.Calculated;
import org.jannocessor.annotation.DomainModel;
import org.jannocessor.collection.api.PowerList;
import org.jannocessor.model.executable.JavaConstructor;
import org.jannocessor.model.executable.JavaInstanceInit;
import org.jannocessor.model.executable.JavaStaticInit;
import org.jannocessor.model.variable.JavaEnumConstant;
import org.jannocessor.model.variable.JavaField;

@DomainModel
public interface AbstractJavaEnum extends AbstractJavaStructure {

	@Override
	@Calculated
	AbstractJavaEnum copy();

	PowerList<JavaEnumConstant> getValues();

	PowerList<JavaField> getFields();

	PowerList<JavaConstructor> getConstructors();

	PowerList<JavaStaticInit> getStaticInits();

	PowerList<JavaInstanceInit> getInstanceInits();

}
