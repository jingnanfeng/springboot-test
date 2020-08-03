package cn.com.nanfneg.redislock.runner;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

/**
 * <>布隆过滤器</>
 * 算法过程：
 * 1首先需要k个hash函数，每一个函数可以把key散列成为1个正式
 * 2、初始化时，需要一个长度为n比较的数组，每个比特为初始化为0
 * 3、某个key加入集合时，用k个hash函数计算出k个散列值，并把数组中对应的比特位置为1
 * 4判断某个key是否存在集合，用k个hash函数计算出k个散列值，并查询数组中对应的比特位，如果所有的比特位都是1，认为在集合中
 *
 *
 * @author liutao
 * @title BloomFilterHelper
 * @description bloomFilter实现
 * @date 2020-08-01 18:52
 */
public class BloomFilterHelper<T> {

    /**
     * 计算hash的执行次数
     */
    private int numHashFunctions;

    /**
     * bit数组长度
     */
    private int bitSize;

    private Funnel<T> funnel;

    public BloomFilterHelper(Funnel<T> funnel,int exepectionInsertions,double fpp){
        Preconditions.checkArgument(funnel != null,"funnel不能为空");
        this.funnel = funnel;
        //计算bit数组的长度
        bitSize = optimalNumOfBits(exepectionInsertions, fpp);
        //计算hash方法执行次数
        numHashFunctions = optimalNumOfHashFunctions(exepectionInsertions,bitSize);
    }

    public int[] murmurHashOffset(T value){
        int[] offset = new int[numHashFunctions];
        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0){
                nextHash = -nextHash;
            }
            offset[i - 1] = nextHash % bitSize;
        }
        return offset;
    }


    /**
     * 计算bit数组的大小
     * @param n
     * @param p
     * @return
     */
    private int optimalNumOfBits(long n,double p){
        if (p == 0){
            //设定最小期望长度
            p = Double.MIN_VALUE;
        }
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 计算hash方法的执行次数
     * @param n
     * @param m
     * @return
     */
    private int optimalNumOfHashFunctions(int n,long m){
        return  Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }


}
