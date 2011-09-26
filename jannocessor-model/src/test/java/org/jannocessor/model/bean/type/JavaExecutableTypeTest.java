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

package org.jannocessor.model.bean.type;

import static org.junit.Assert.*;

import java.util.List;

import org.jannocessor.model.type.JavaType;
import org.jannocessor.model.util.Code;
import org.junit.Test;

public class JavaExecutableTypeTest {

    @Test
    public void testInstantiation() {
        JavaType elementType = Code.executableType(List.class, String.class);
        assertNotNull(elementType);
    }

}