package nuc.edu.ks;

/**
 * @author 薛东
 * @date 2021/5/8 17:36
 */
public class Article {
    public int weight;
    public int value;
    public double valueDensity;
    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        valueDensity = value * 1.0 / weight;
    }
    @Override
    public String toString() {
        return "Article [weight=" + weight + ", value=" + value + ", valueDensity=" + valueDensity + "]";
    }
}
