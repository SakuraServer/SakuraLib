/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/26 9:10:08
 */
package net.syamn.utils;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * ListUtil (ListUtil.java)
 * @author syam(syamn)
 */
public class ListUtil {
    private static final Random rand = new Random();
    
    /**
     * Intコレクションの合計値を返す
     * @param intMap
     * @return
     */
    public static int sumInts(final Collection<Integer> intMap){
        int i = 0;
        
        for (int value : intMap){
            i += value;
        }
        
        return i;
    }
    
    /**
     * リストの中からランダムで1つ要素を返す
     * @param items 対象のリスト
     * @return ランダムな要素
     */
    public static <T> T getRandom(final List<T> items){
        return items.get(rand.nextInt(items.size()));
    }
}
