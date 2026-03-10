class GammaTransform {

    /**
     * Gamma transform: s = c * (r/255)^gamma
     */
    public static int[][] gammaTransform(int[][] imgArray, double c, double gamma) {
        int height = imgArray.length;
        int width = imgArray[0].length;
        int[][] result = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r = imgArray[i][j];
                double s = c * Math.pow((r / 255.0), gamma);
                result[i][j] = (int) s;
            }
        }

        return result;
    }

    public static int[][] gammaTransform(int[][] imgArray) {
        return gammaTransform(imgArray, 255.0, 1.0);
    }

    public static void main(String[] args) {
        int[][] testImage = {
            {50, 100},
            {150, 200}
        };

        int[][] brightImage = gammaTransform(testImage, 255.0, 0.5);

        System.out.println("Gamma 轉換結果:");
        for (int i = 0; i < brightImage.length; i++) {
            for (int j = 0; j < brightImage[0].length; j++) {
                System.out.print(brightImage[i][j] + " ");
            }
            System.out.println();
        }
    }
}
