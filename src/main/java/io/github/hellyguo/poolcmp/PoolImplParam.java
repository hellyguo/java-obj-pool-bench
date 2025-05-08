/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the License); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.hellyguo.poolcmp;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class PoolImplParam {

    @Param({
            "ApacheCommonsPool001StackPool",
            "ApacheCommonsPool002Pool",
            "ApacheCommonsPool003SoftRefPool",
            "ApacheCommonsPool2001Pool",
            "ApacheCommonsPool2002SoftRefPool",
            "BeeOp001FastPool",
            "BeeOp002ObjectSource",
            "CoralPool001ArrayPool",
            "CoralPool002LinkedPool",
            "EraaSoftPool001",
            "FastPool001",
            "FastPool002Disruptor",
            "Frogspawn001",
            "GenericObjectPool001",
            "JavaNew001",
            "KOPool001",
            "LitePool001",
            "StormPot001BlazePool",
            "StormPot002QueuePool",
            "ViburPool001",
    })
    public PoolImplDesc desc;

}
