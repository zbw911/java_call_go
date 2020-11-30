package com.zhangbaowei.grpcclient.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Random;

/**
 * @author zhangbaowei
 */
public class HelloWorldFactory extends BasePooledObjectFactory<HelloWorldClientSingle> {

    private final String target;

    public HelloWorldFactory(String target) {
        this.target = target;
    }

    @Override
    public HelloWorldClientSingle create() throws Exception {

        String[] targets = this.target.split(",");
        String url = targets[randInt(0, targets.length - 1)];
        return new HelloWorldClientSingle(url);
    }

    @Override
    public PooledObject<HelloWorldClientSingle> wrap(HelloWorldClientSingle helloWorldClientSingle) {
        return new DefaultPooledObject<>(helloWorldClientSingle);
    }

    @Override
    public void destroyObject(PooledObject<HelloWorldClientSingle> p) throws Exception {
        p.getObject().shutdown();
        super.destroyObject(p);
    }

    private static int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}