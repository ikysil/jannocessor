/**
 * Copyright 2011 Nikolche Mihajlovski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jannocessor.model.bean.executable;

import java.util.List;

import org.jannocessor.collection.Power;
import org.jannocessor.data.JavaConstructorData;
import org.jannocessor.model.JavaElementKind;
import org.jannocessor.model.executable.JavaConstructor;
import org.jannocessor.model.modifier.ConstructorModifiers;
import org.jannocessor.model.structure.JavaMetadata;
import org.jannocessor.model.structure.JavaTypeParameter;
import org.jannocessor.model.type.JavaType;
import org.jannocessor.model.util.New;
import org.jannocessor.model.variable.JavaParameter;

public class JavaConstructorBean extends JavaConstructorData implements
		JavaConstructor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3485905546796025662L;

	public JavaConstructorBean(ConstructorModifiers modifiers,
			List<JavaParameter> params, List<JavaType> thrownTypes,
			List<JavaTypeParameter> typeParameters) {
		this.setBody(New.body());
		this.setModifiers(modifiers);
		this.setParameters(children(Power.list(params)));
		this.setThrownTypes(children(Power.list(thrownTypes)));
		this.setTypeParameters(children(Power.list(typeParameters)));
		this.setMetadata(children(Power.emptyList(JavaMetadata.class)));

		// TODO: calculate and set isVarArgs
		this.setVarArgs(false);

		this.setKind(JavaElementKind.CONSTRUCTOR);
		this.setCode(New.code(JavaConstructor.class));
	}

}
