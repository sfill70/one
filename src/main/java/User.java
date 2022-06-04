
public class User {
    int rank;
    int progress;

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    void incProgress(int rankAction) {
        if (this.rank == 8) {
            return;
        }
        if (rankAction == 0 || rankAction > 8 || rankAction < -8) {
            throw new IllegalArgumentException("Invalid Range!!!");
        }
        if (rank == rankAction) {
            progress = progress + 3;
        }
        if (rank > 0 && rankAction < 0) {
            rankAction++;
        }
        if (rankAction > 0 && rank < 0) {
            rankAction--;
        }
        if (rank - rankAction == 1) {
            progress = progress + 1;
        }
        if (rank - rankAction >= 2) {
            return;
        }
        if (rank - rankAction <= -1) {
            progress = progress + (10 * (rank - rankAction) * (rank - rankAction));
        }
        while (progress >= 100) {
            rank = rank + 1;
            if (rank == 8) {
                progress = 0;
                return;
            }
            if (rank == 0) {
                rank = 1;
            }
            progress = progress - 100;
        }
    }

    /* private int[] ranks = {-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8};
  private int curRank = 0;
  public int progress = 0;
  public int rank = -8;
  public void incProgress(int kRank) {
    kRank = Arrays.binarySearch(ranks, kRank);
    if(kRank < 0) throw new RuntimeException("Invalid rank");
    if(ranks[curRank] == 8) return;
    if(kRank == curRank) progress += 3;
    else if(kRank == curRank - 1) progress++;
    else if(kRank > curRank) {
      int diff = kRank - curRank;
      progress += 10 * diff * diff;
    }
    while(progress >= 100) {
      curRank++;
      updateRank();
      progress -= 100;
      if(ranks[curRank] == 8) {
        progress = 0;
        return;
      }
    }
  }
  private void updateRank() {
    rank = ranks[curRank];
  }*/

    /* private static int MAX_RANK = 8, MAX_PROGRESS = 100;
    private static Set<Integer> RANKS = new HashSet<Integer>( Arrays.asList(-8,-7,-6,-5,-4,-3,-2,-1, 1, 2, 3, 4, 5, 6, 7, 8) );

    int rank, progress;

    public User() {
        rank = -8;
        progress = 0;
    }

    public void incProgress(int kataRank) {
        if (!RANKS.contains(kataRank)) throw new RuntimeException("Invalid rank");
        int dRank = kataRank - rank + (rank > 0 ? 1:-1) * (kataRank*rank < 0 ? 1:0);
        updateProgress(dRank > 0  ? 10 * dRank * dRank:
                       dRank == 0 ? 3:
                       dRank > -2 ? 1:0);
    }

    private void updateProgress(int p) {
        int nLevel = (progress+p) / MAX_PROGRESS;
        rank += nLevel + (RANKS.contains(rank+nLevel) ? 0:1);
        progress = rank >= MAX_RANK ? 0 : (progress+p) % MAX_PROGRESS;
    }*/
}
