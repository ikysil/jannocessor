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

package org.jannocessor.proxy;

import javax.annotation.Generated;
import org.jannocessor.proxy.AbstractJavaExecutableProxy;
import org.jannocessor.model.executable.JavaMethod;
import org.jannocessor.data.JavaMethodData;
import org.jannocessor.model.util.ModelUtils;
import org.jannocessor.collection.api.PowerList;
import org.jannocessor.model.structure.JavaTypeParameter;
import org.jannocessor.model.type.JavaType;
import org.jannocessor.model.variable.JavaParameter;
import org.jannocessor.model.modifier.MethodModifiers;
import org.jannocessor.model.structure.JavaMetadata;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jannocessor.util.TypeSpecificStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jannocessor.model.util.ToStringUtil;


@Generated("JAnnocessor-bootstraped")
public class JavaMethodProxy extends AbstractJavaExecutableProxy implements JavaMethod {

	private static final long serialVersionUID = 1L;

    private transient JavaMethod adapter;

    private JavaMethodData data;

    public JavaMethodProxy(JavaMethod adapter, JavaMethodData data) {
        super(adapter, data);
        this.adapter = adapter;
        this.data = data;
    }

	private boolean getTypeParametersInitialized = false;

	private boolean getReturnTypeInitialized = false;

	private boolean getParametersInitialized = false;

	private boolean isVarArgsInitialized = false;

	private boolean getThrownTypesInitialized = false;

	private boolean getModifiersInitialized = false;

	private boolean getMetadataInitialized = false;


	@Override
    public JavaMethod copy() {
        return ModelUtils.copy(this);
    }

	@Override
    public PowerList<JavaTypeParameter> getTypeParameters() {
        if (!getTypeParametersInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setTypeParameters(ModelUtils.parentedList(adapter.getTypeParameters(), this));
			getTypeParametersInitialized = true;
        }

        return data.getTypeParameters();
    }

	@Override
    public JavaType getReturnType() {
        if (!getReturnTypeInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setReturnType(adapter.getReturnType());
			getReturnTypeInitialized = true;
        }

        return data.getReturnType();
    }

	@Override
    public PowerList<JavaParameter> getParameters() {
        if (!getParametersInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setParameters(ModelUtils.parentedList(adapter.getParameters(), this));
			getParametersInitialized = true;
        }

        return data.getParameters();
    }

	@Override
    public Boolean isVarArgs() {
        if (!isVarArgsInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setVarArgs(adapter.isVarArgs());
			isVarArgsInitialized = true;
        }

        return data.isVarArgs();
    }

	@Override
    public PowerList<JavaType> getThrownTypes() {
        if (!getThrownTypesInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setThrownTypes(ModelUtils.parentedList(adapter.getThrownTypes(), this));
			getThrownTypesInitialized = true;
        }

        return data.getThrownTypes();
    }

	@Override
    public MethodModifiers getModifiers() {
        if (!getModifiersInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setModifiers(adapter.getModifiers());
			getModifiersInitialized = true;
        }

        return data.getModifiers();
    }

	@Override
    public PowerList<JavaMetadata> getMetadata() {
        if (!getMetadataInitialized) {
			if (adapter == null) {
				throw new IllegalStateException("Invalid model copy!");
			}
            data.setMetadata(ModelUtils.parentedList(adapter.getMetadata(), this));
			getMetadataInitialized = true;
        }

        return data.getMetadata();
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof JavaMethod)) {
			return false;
		}

		JavaMethod other = (JavaMethod) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(other))
				.append(this.getTypeParameters(), other.getTypeParameters())
				.append(this.getReturnType(), other.getReturnType())
				.append(this.getParameters(), other.getParameters())
				.append(this.isVarArgs(), other.isVarArgs())
				.append(this.getThrownTypes(), other.getThrownTypes())
				.append(this.getModifiers(), other.getModifiers())
				.append(this.getMetadata(), other.getMetadata())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.getTypeParameters())
				.append(this.getReturnType())
				.append(this.getParameters())
				.append(this.isVarArgs())
				.append(this.getThrownTypes())
				.append(this.getModifiers())
				.append(this.getMetadata())
				.toHashCode();
	}

	@Override
	public String toString() {
		TypeSpecificStyle style = new TypeSpecificStyle(JavaMethod.class);
		ToStringBuilder builder = new ToStringBuilder(this, style);
		appendDescription(builder);
		return builder.toString();
	}

	@Override
	protected void appendDescription(ToStringBuilder builder) {
        super.appendDescription(builder);
        builder.append("typeParameters", ToStringUtil.describe(this.getTypeParameters()));
        builder.append("returnType", ToStringUtil.describe(this.getReturnType()));
        builder.append("parameters", ToStringUtil.describe(this.getParameters()));
        builder.append("_isVarArgs", ToStringUtil.describe(this.isVarArgs()));
        builder.append("thrownTypes", ToStringUtil.describe(this.getThrownTypes()));
        builder.append("modifiers", ToStringUtil.describe(this.getModifiers()));
        builder.append("metadata", ToStringUtil.describe(this.getMetadata()));
	}

	@Override
	protected void loadAllData() {
		super.loadAllData();

		// load all values from the adapter to the data bean
		this.getTypeParameters();
		this.getReturnType();
		this.getParameters();
		this.isVarArgs();
		this.getThrownTypes();
		this.getModifiers();
		this.getMetadata();
	}

}


