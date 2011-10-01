/**
 * Copyright 2011 jannocessor.org
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

package org.jannocessor.data;

import javax.annotation.Generated;
import org.jannocessor.data.JavaElementData;
import org.jannocessor.model.structure.AbstractJavaStructure;
import org.jannocessor.model.Name;
import org.jannocessor.model.type.JavaType;
import org.jannocessor.collection.api.PowerList;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


@Generated("JAnnocessor-bootstraped")
public class AbstractJavaStructureData extends JavaElementData implements AbstractJavaStructure {

    private String nesting;

    private Name packageName;

    private Name qualifiedName;

    private JavaType superclass;

    private PowerList<JavaType> interfaces;


    public String getNesting() {
        return this.nesting;
    }

    public void setNesting(String value) {
        this.nesting = value;
    }

    public Name getPackageName() {
        return this.packageName;
    }

    public void setPackageName(Name value) {
        this.packageName = value;
    }

    public Name getQualifiedName() {
        return this.qualifiedName;
    }

    public void setQualifiedName(Name value) {
        this.qualifiedName = value;
    }

    public JavaType getSuperclass() {
        return this.superclass;
    }

    public void setSuperclass(JavaType value) {
        this.superclass = value;
    }

    public PowerList<JavaType> getInterfaces() {
        return this.interfaces;
    }

    public void setInterfaces(PowerList<JavaType> value) {
        this.interfaces = value;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AbstractJavaStructure)) {
			return false;
		}

		AbstractJavaStructure other = (AbstractJavaStructure) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(other))
				.append(this.getNesting(), other.getNesting())
				.append(this.getPackageName(), other.getPackageName())
				.append(this.getQualifiedName(), other.getQualifiedName())
				.append(this.getSuperclass(), other.getSuperclass())
				.append(this.getInterfaces(), other.getInterfaces())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.getNesting())
				.append(this.getPackageName())
				.append(this.getQualifiedName())
				.append(this.getSuperclass())
				.append(this.getInterfaces())
				.toHashCode();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		appendDescription(builder);
		return builder.toString();
	}

	@Override
	protected void appendDescription(ToStringBuilder builder) {
        super.appendDescription(builder);
        builder.append("nesting", this.getNesting());
        builder.append("packageName", this.getPackageName());
        builder.append("qualifiedName", this.getQualifiedName());
        builder.append("superclass", this.getSuperclass());
        builder.append("interfaces", this.getInterfaces());
	}

}

