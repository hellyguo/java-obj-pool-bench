package io.github.hellyguo.poolcmp;

import io.github.hellyguo.poolcmp.impl.ApacheCommonsPool001StackPool;
import io.github.hellyguo.poolcmp.impl.ApacheCommonsPool002Pool;
import io.github.hellyguo.poolcmp.impl.ApacheCommonsPool003SoftRefPool;
import io.github.hellyguo.poolcmp.impl.ApacheCommonsPool2001Pool;
import io.github.hellyguo.poolcmp.impl.ApacheCommonsPool2002SoftRefPool;
import io.github.hellyguo.poolcmp.impl.BeeOp001FastPool;
import io.github.hellyguo.poolcmp.impl.BeeOp002ObjectSource;
import io.github.hellyguo.poolcmp.impl.CoralPool001ArrayPool;
import io.github.hellyguo.poolcmp.impl.CoralPool002LinkedPool;
import io.github.hellyguo.poolcmp.impl.FastPool001;
import io.github.hellyguo.poolcmp.impl.FastPool002Disruptor;
import io.github.hellyguo.poolcmp.impl.Frogspawn001;
import io.github.hellyguo.poolcmp.impl.FuriousObjectPool001;
import io.github.hellyguo.poolcmp.impl.JavaNew001;
import io.github.hellyguo.poolcmp.impl.KOPool001;
import io.github.hellyguo.poolcmp.impl.LitePool001;
import io.github.hellyguo.poolcmp.impl.NoMoreInstance001SweepClean;
import io.github.hellyguo.poolcmp.impl.NoMoreInstance002Clean;
import io.github.hellyguo.poolcmp.impl.Recall001;
import io.github.hellyguo.poolcmp.impl.StormPot001;
import io.github.hellyguo.poolcmp.impl.ViburPool001;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-07 19:06
 */
public enum PoolImplDesc {
    // new
    JavaNew001(new JavaNew001()),
    // impl
    ApacheCommonsPool001StackPool(new ApacheCommonsPool001StackPool()),
    ApacheCommonsPool002Pool(new ApacheCommonsPool002Pool()),
    ApacheCommonsPool003SoftRefPool(new ApacheCommonsPool003SoftRefPool()),
    ApacheCommonsPool2001Pool(new ApacheCommonsPool2001Pool()),
    ApacheCommonsPool2002SoftRefPool(new ApacheCommonsPool2002SoftRefPool()),
    BeeOp001FastPool(new BeeOp001FastPool()),
    BeeOp002ObjectSource(new BeeOp002ObjectSource()),
    CoralPool001ArrayPool(new CoralPool001ArrayPool()),
    CoralPool002LinkedPool(new CoralPool002LinkedPool()),
    FastPool001(new FastPool001()),
    FastPool002Disruptor(new FastPool002Disruptor()),
    Frogspawn001(new Frogspawn001()),
    FuriousObjectPool001(new FuriousObjectPool001()),
    KOPool001(new KOPool001()),
    LitePool001(new LitePool001()),
    NoMoreInstance001SweepClean(new NoMoreInstance001SweepClean()),
    NoMoreInstance002Clean(new NoMoreInstance002Clean()),
    Recall001(new Recall001()),
    StormPot001(new StormPot001()),
    ViburPool001(new ViburPool001());

    private final PoolImplementor implementor;

    PoolImplDesc(PoolImplementor implementor) {
        this.implementor = implementor;
    }

    public PoolImplementor getImplementor() {
        return implementor;
    }
}
