package strategy;

// Strategy pattern: different compression strategies for a compressor.
interface CompressionStrategy {
    byte[] compress(byte[] data);
}

class ZipCompression implements CompressionStrategy {
    public byte[] compress(byte[] data) {
        System.out.println("Compressing using ZIP strategy (simulated).");
        return data; // simulated - return same data
    }
}

class RarCompression implements CompressionStrategy {
    public byte[] compress(byte[] data) {
        System.out.println("Compressing using RAR strategy (simulated).");
        return data;
    }
}

class Compressor {
    private CompressionStrategy strategy;
    public void setStrategy(CompressionStrategy s){ this.strategy = s; }
    public void compress(byte[] data) {
        if (strategy == null) throw new IllegalStateException("Strategy not set");
        strategy.compress(data);
    }
}

public class Main {
    public static void main(String[] args) {
        Compressor c = new Compressor();
        c.setStrategy(new ZipCompression());
        c.compress("hello".getBytes());
        c.setStrategy(new RarCompression());
        c.compress("hello".getBytes());
    }
}
