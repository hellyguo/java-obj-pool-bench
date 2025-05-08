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
package io.github.hellyguo.poolcmp.domain;

import cn.itcraft.frogspawn.Resettable;
import io.github.hellyguo.poolcmp.misc.ValPojo;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectState;

import java.io.PrintWriter;
import java.util.Deque;

/**
 * @author Helly Guo
 * <p>
 * Created on 8/25/21 1:16 AM
 */
public class DemoPojo implements ValPojo, PooledObject<DemoPojo>, Resettable {

    private int val1;
    private long val2;
    private double val3;
    private String val4;

    private int allocId = -1;

    @Override
    public int getVal1() {
        return val1;
    }

    @Override
    public void setVal1(int val1) {
        this.val1 = val1;
    }

    @Override
    public long getVal2() {
        return val2;
    }

    @Override
    public void setVal2(long val2) {
        this.val2 = val2;
    }

    @Override
    public double getVal3() {
        return val3;
    }

    @Override
    public void setVal3(double val3) {
        this.val3 = val3;
    }

    @Override
    public String getVal4() {
        return val4;
    }

    @Override
    public void setVal4(String val4) {
        this.val4 = val4;
    }

    @Override
    public boolean allocate() {
        return true;
    }

    @Override
    public int compareTo(PooledObject<DemoPojo> pooledObject) {
        return 0;
    }

    @Override
    public boolean deallocate() {
        return true;
    }

    @Override
    public boolean endEvictionTest(Deque<PooledObject<DemoPojo>> deque) {
        return true;
    }

    @Override
    public long getActiveTimeMillis() {
        return 0;
    }

    @Override
    public long getCreateTime() {
        return 0;
    }

    @Override
    public long getIdleTimeMillis() {
        return 0;
    }

    @Override
    public long getLastBorrowTime() {
        return 0;
    }

    @Override
    public long getLastReturnTime() {
        return 0;
    }

    @Override
    public long getLastUsedTime() {
        return 0;
    }

    @Override
    public DemoPojo getObject() {
        return this;
    }

    @Override
    public PooledObjectState getState() {
        return PooledObjectState.ALLOCATED;
    }

    @Override
    public void invalidate() {
    }

    @Override
    public void markAbandoned() {
    }

    @Override
    public void markReturning() {
    }

    @Override
    public void printStackTrace(PrintWriter printWriter) {
    }

    @Override
    public void setLogAbandoned(boolean b) {
    }

    @Override
    public boolean startEvictionTest() {
        return true;
    }

    @Override
    public void use() {
    }

    @Override
    public void reset() {
        this.val1 = 0;
        this.val2 = 0L;
        this.val3 = 0D;
        this.val4 = "";
    }

    @Override
    public int getMarkedId() {
        return allocId;
    }

    @Override
    public void markId(int id) {
        this.allocId = id;
    }

    @Override
    public String toString() {
        return "DemoPojo{" +
                "val1=" + val1 +
                ", val2=" + val2 +
                ", val3=" + val3 +
                ", val4='" + val4 + '\'' +
                '}';
    }

    public DemoPojo resetThis() {
        this.val1 = 0;
        this.val2 = 0L;
        this.val3 = 0D;
        this.val4 = "";
        return this;
    }
}
