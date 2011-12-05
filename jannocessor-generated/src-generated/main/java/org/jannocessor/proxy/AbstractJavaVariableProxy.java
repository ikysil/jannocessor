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
import org.jannocessor.proxy.JavaElementProxy;
import org.jannocessor.model.variable.AbstractJavaVariable;
import org.jannocessor.data.AbstractJavaVariableData;
import org.jannocessor.model.util.ModelUtils;
import org.jannocessor.model.JavaElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jannocessor.util.TypeSpecificStyle;
import org.apache.commons.lang.builder.ToStringBuilder;


@Generated("JAnnocessor-bootstraped")
public class AbstractJavaVariableProxy extends JavaElementProxy implements AbstractJavaVariable {

	private static final long serialVersionUID = 1L;

    private transient AbstractJavaVariable adapter;

    private AbstractJavaVariableData data;

    public AbstractJavaVariableProxy(AbstractJavaVariable adapter, AbstractJavaVariableData data) {
        super(adapter, data);
        this.adapter = adapter;
        this.data = data;
    }


	@Override
    public AbstractJavaVariable copy() {
        return ModelUtils.copy(this);
    }

	@Override
    public JavaElement getParent() {
        return super.retrieveParent();
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AbstractJavaVariable)) {
			return false;
		}

		AbstractJavaVariable other = (AbstractJavaVariable) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(other))
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.toHashCode();
	}

	@Override
	public String toString() {
		TypeSpecificStyle style = new TypeSpecificStyle(AbstractJavaVariable.class);
		ToStringBuilder builder = new ToStringBuilder(this, style);
		appendDescription(builder);
		return builder.toString();
	}

	@Override
	protected void appendDescription(ToStringBuilder builder) {
        super.appendDescription(builder);
	}

	@Override
	protected void loadAllData() {
		super.loadAllData();

		// load all values from the adapter to the data bean
	}

}


