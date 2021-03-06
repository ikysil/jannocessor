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

package org.jannocessor.adapter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.jannocessor.collection.Power;
import org.jannocessor.collection.api.PowerList;
import org.jannocessor.model.JavaElement;
import org.jannocessor.model.Name;
import org.jannocessor.model.structure.JavaMetadata;
import org.jannocessor.model.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAdapter {

	enum TextMode {
		FULL, UP, DOWN
	}

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();

	private final PropertyUtilsBean propertyUtils = beanUtils
			.getPropertyUtils();

	private final Elements elementUtils;

	private final Types typeUtils;

	public AbstractAdapter(Elements elementUtils, Types typeUtils) {
		this.elementUtils = elementUtils;
		this.typeUtils = typeUtils;
	}

	protected <T extends JavaElement> T getElementAdapter(Element element,
			Class<T> clazz) {
		return AdapterFactory.getElementModel(element, clazz, elementUtils,
				typeUtils);
	}

	protected <T extends JavaElement> PowerList<T> getElementsAdapters(
			List<? extends Element> elements, Class<T> clazz) {
		PowerList<T> adapters = Power.emptyList();

		for (Element element : elements) {
			adapters.add(getElementAdapter(element, clazz));
		}

		return adapters;
	}

	protected <T extends JavaType> T getTypeAdapter(TypeMirror typeMirror,
			Class<T> clazz) {
		return AdapterFactory.getTypeModel(typeMirror, clazz, elementUtils,
				typeUtils);
	}

	protected JavaType getTypeAdapter(TypeMirror typeMirror) {
		return getTypeAdapter(typeMirror, JavaType.class);
	}

	protected <T extends JavaType> PowerList<T> getTypeAdapters(
			List<? extends TypeMirror> typeMirrors, Class<T> clazz) {
		PowerList<T> types = Power.emptyList();

		for (TypeMirror typeMirror : typeMirrors) {
			types.add(getTypeAdapter(typeMirror, clazz));
		}

		return types;
	}

	protected PowerList<JavaType> getTypeAdapters(
			List<? extends TypeMirror> typeMirrors) {
		return getTypeAdapters(typeMirrors, JavaType.class);
	}

	protected JavaMetadata getMetadataAdapter(AnnotationMirror annotationMirror) {
		return AdapterFactory.getMetadataAdapter(annotationMirror,
				elementUtils, typeUtils);
	}

	protected Name getNameAdapter(Object value) {
		return value != null ? AdapterFactory.getNameModel(String
				.valueOf(value)) : null;
	}

	protected Elements getElementUtils() {
		return elementUtils;
	}

	protected Types getTypeUtils() {
		return typeUtils;
	}

	@Override
	public String toString() {
		return asText(TextMode.FULL);
	}

	@SuppressWarnings("unused")
	private String showAsParent() {
		return asText(TextMode.UP);
	}

	private String showAsChild() {
		return asText(TextMode.DOWN);
	}

	private String asText(TextMode mode) {
		try {
			StringBuilder sb = new StringBuilder();

			PropertyDescriptor[] descriptors = propertyUtils
					.getPropertyDescriptors(this);

			sb.append("{");
			boolean isFirst = true;
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				Object value;
				try {
					value = propertyUtils.getProperty(this, name);
				} catch (Exception e) {
					logger.error("Error occured while reading property: "
							+ name, e);
					e.printStackTrace();
					value = "[ERROR!]";
				}

				if (!isPropertyForbidden(name, value, mode)) {
					if (!isFirst) {
						sb.append(", ");
					}
					isFirst = false;

					sb.append(name + "=" + showProperty(name, value));
				}
			}
			sb.append("}");

			return sb.toString();
		} catch (Exception e) {
			logger.error(
					"Error occured while constructing element textual representation",
					e);
			e.printStackTrace();
			return "[ERROR!]";
		}

	}

	private boolean isPropertyForbidden(String name, Object value, TextMode mode) {
		if (name.equals("class")) {
			return true;
		} else {
			if (mode.equals(TextMode.FULL)) {
				return false;
			} else {
				return (value == null) || (value instanceof Collection)
						|| (value instanceof JavaElement);
			}
		}
	}

	private String showProperty(String name, Object value)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (value != null) {
			if (value instanceof AbstractAdapter) {
				// AbstractAdapter adapter = (AbstractAdapter) value;
				if (name.equals("parent")) {
					return "[...]"; // adapter.showAsParent();
				} else {
					return "[...]"; // adapter.showAsChild();
				}
			} else if (value instanceof Collection) {
				Collection<?> collection = (Collection<?>) value;
				return showMiniChildCollection(collection); // showChildCollection
			} else {
				return value.toString();
			}
		} else {
			return "NULL";
		}
	}

	private String showMiniChildCollection(Collection<?> collection) {
		return "[" + collection.size() + " items]";
	}

	@SuppressWarnings("unused")
	private String showChildCollection(Collection<?> collection) {
		StringBuilder sb = new StringBuilder();

		Iterator<?> iterator = collection.iterator();
		sb.append("[");

		while (iterator.hasNext()) {
			Object item = iterator.next();
			if (item instanceof AbstractAdapter) {
				AbstractAdapter adapter = (AbstractAdapter) item;
				sb.append(adapter.showAsChild());
			} else {
				sb.append(item.toString());
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");

		return sb.toString();
	}

	protected abstract Class<?> getAdaptedInterface();

	protected RuntimeException calculatedMethodException() {
		return new IllegalStateException(
				"This method should be automatically calculated by ModelUtils!");
	}

}
