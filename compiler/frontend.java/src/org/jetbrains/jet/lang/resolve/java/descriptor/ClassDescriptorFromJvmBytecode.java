/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.lang.resolve.java.descriptor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.lang.descriptors.ClassKind;
import org.jetbrains.jet.lang.descriptors.ConstructorDescriptor;
import org.jetbrains.jet.lang.descriptors.DeclarationDescriptor;
import org.jetbrains.jet.lang.descriptors.MutableClassDescriptorLite;
import org.jetbrains.jet.lang.resolve.java.JavaDescriptorResolver;
import org.jetbrains.jet.lang.resolve.java.provider.ClassPsiDeclarationProvider;
import org.jetbrains.jet.lang.resolve.lazy.LazyClassDescriptor;

import java.util.Collection;

/**
 * @author Stepan Koltsov
 *
 * @see LazyClassDescriptor
 */
public class ClassDescriptorFromJvmBytecode extends MutableClassDescriptorLite {
    @NotNull
    private final JavaDescriptorResolver javaDescriptorResolver;

    @NotNull
    private final ClassPsiDeclarationProvider resolverBinaryClassData;


    // lazy
    private Collection<ConstructorDescriptor> constructors;

    public ClassDescriptorFromJvmBytecode(
            @NotNull DeclarationDescriptor containingDeclaration, @NotNull ClassKind kind,
            @NotNull JavaDescriptorResolver javaDescriptorResolver,
            @NotNull ClassPsiDeclarationProvider data
    ) {
        super(containingDeclaration, kind);
        this.javaDescriptorResolver = javaDescriptorResolver;
        this.resolverBinaryClassData = data;
    }

    @NotNull
    @Override
    public Collection<ConstructorDescriptor> getConstructors() {
        if (constructors == null) {
            this.constructors = javaDescriptorResolver.resolveConstructors(resolverBinaryClassData, this);
        }
        return constructors;
    }
}
