import java.util.Scanner;

public class MatrixProduct {
    public static void OnMult(int m_ar, int m_br) {
        long Time1, Time2;

        double temp;
        double[] pha, phb, phc;

        pha = new double[m_ar * m_ar];
        phb = new double[m_ar * m_ar];
        phc = new double[m_ar * m_ar];

        for (int i = 0; i < m_ar; i++) {
            for (int j = 0; j < m_ar; j++) {
                pha[i * m_ar + j] = 1.0;
            }
        }

        for (int i = 0; i < m_br; i++) {
            for (int j = 0; j < m_br; j++) {
                phb[i * m_br + j] = i + 1;
            }
        }

        Time1 = System.currentTimeMillis();

        for (int i = 0; i < m_ar; i++) {
            for (int j = 0; j < m_br; j++) {
                temp = 0;
                for (int k = 0; k < m_ar; k++) {
                    temp += pha[i * m_ar + k] * phb[k * m_br + j];
                }
                phc[i * m_ar + j] = temp;
            }
        }

        Time2 = System.currentTimeMillis();

        System.out.printf("Time: %.3f seconds\n", (Time2 - Time1) / 1000.0);

        System.out.println("Result matrix: ");
        for (int j = 0; j < Math.min(10, m_br); j++) {
            System.out.print(phc[j] + " ");
        }
        System.out.println();

    }

    public static void onMultLine(int m_ar, int m_br) {
        long Time1, Time2;

        double temp;
        double[] pha, phb, phc;

        pha = new double[m_ar * m_ar];
        phb = new double[m_ar * m_ar];
        phc = new double[m_ar * m_ar];

        for (int i = 0; i < m_ar; i++) {
            for (int j = 0; j < m_ar; j++) {
                pha[i * m_ar + j] = 1.0;
            }
        }

        for (int i = 0; i < m_br; i++) {
            for (int j = 0; j < m_br; j++) {
                phb[i * m_br + j] = i + 1;
            }
        }

        Time1 = System.currentTimeMillis();

        for (int i = 0; i < m_ar; i++) {
            for (int k = 0; k < m_br; k++) {
                for (int j = 0; j < m_ar; j++) {
                    phc[i * m_ar + j]  += pha[i * m_ar + k] * phb[k * m_br + j];
                }
            }
        }

        Time2 = System.currentTimeMillis();

        System.out.printf("Time: %.3f seconds\n", (Time2 - Time1) / 1000.0);

        System.out.println("Result matrix: ");
        for (int j = 0; j < Math.min(10, m_br); j++) {
            System.out.print(phc[j] + " ");
        }
        System.out.println();

    }

    public static void onMultBlock(int m_ar, int m_br, int bk_size) {
        long startTime = System.currentTimeMillis();
        
        double[] pha = new double[m_ar * m_ar];
        double[] phb = new double[m_ar * m_ar];
        double[] phc = new double[m_ar * m_ar];
        
        for (int i = 0; i < m_ar; i++)
            for (int j = 0; j < m_ar; j++)
                pha[i * m_ar + j] = 1.0;
        
        for (int i = 0; i < m_br; i++)
            for (int j = 0; j < m_br; j++)
                phb[i * m_br + j] = i + 1;
        
        int n = m_ar / bk_size;
        
        for (int i = 0; i < m_ar; i += bk_size)
            for (int k = 0; k < m_ar; k += bk_size)
                for (int j = 0; j < m_ar; j += bk_size)
                    for (int ii = i; ii < Math.min(i + bk_size, m_ar); ii++)
                        for (int kk = k; kk < Math.min(k + bk_size, m_ar); kk++)
                            for (int jj = j; jj < Math.min(j + bk_size, m_ar); jj++)
                                phc[ii * m_ar + jj] += pha[ii * m_ar + kk] * phb[kk * m_br + jj];

        
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        
        System.out.println("Result matrix: ");
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < Math.min(10, m_br); j++)
                System.out.print(phc[j] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int op = 1;

        do {
            System.out.print("\n1. Multiplication\n2. Line Multiplication\n3. Block Multiplication\nSelection?: ");
            op = scanner.nextInt();

            if (op == 0)
                break;

            System.out.print("Dimensions: lins=cols ? ");
            int dimensions = scanner.nextInt();

            if (op == 1) {
                OnMult(dimensions, dimensions);
            } else if (op == 2) {
                onMultLine(dimensions, dimensions);
            } else if (op == 3){
                System.out.print("Block Size? ");
                int block_size = scanner.nextInt();
                onMultBlock(dimensions, dimensions, block_size);
            } else {
                System.out.println("Invalid multiplication type.");
            }
        } while (op != 0);

        scanner.close();
    }
}
