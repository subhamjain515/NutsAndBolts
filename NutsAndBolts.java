import java.util.Iterator;

/*  Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n
 *  bolts. The goal is to find the corresponding pairs of nuts and bolts.
 *  Each nut fits exactly one bolt and each bolt fits exactly one nut.
 *  By fitting a nut and a bolt together, the carpenter can see which one is
 *  bigger (but the carpenter cannot compare two nuts or two bolts directly).
 *  Design an algorithm for the problem that uses at most proportional to
 *  n log n compares (probabilistically). */

public class NutsAndBolts implements Iterable<String>
{
		private final char nuts[];
		private final char bolts[];
		
		public NutsAndBolts(char nuts[], char bolts[])
		{
			if(nuts==null)
				throw new IllegalArgumentException();
			else if(bolts==null)
				throw new IllegalArgumentException();
			else
			{
				this.nuts=nuts.clone();
				this.bolts=bolts.clone();
				matchNutsAndBolts(this.nuts, this.bolts, 0, nuts.length-1);
			}
		}
		
		
		private static void matchNutsAndBolts(char nuts[], char bolts[], int lo, int hi)
		{
			if(lo<hi)
			{
				int pivot=partition(nuts,lo,hi,bolts[hi]);
				
				partition(bolts,lo,hi,nuts[pivot]);
				
				matchNutsAndBolts(nuts,bolts,lo,pivot-1);
				matchNutsAndBolts(nuts,bolts,pivot+1,hi);
			}
		}
		
		
		
		public static int partition(char a[], int lo, int hi, char pivot)
		{
				int i=lo,j=lo;
				
				while(j<hi)
				{
					if(less(a[j],pivot))
					{
						exch(a,i,j);
						i++;
					}
					else if(a[j]==pivot)
					{
						exch(a,j,hi);
						j--;
					}
					
					j++;
				}
				
				exch(a,i,hi);
				return i;
		}
		
		
		private static void exch(char a[], int i, int j)
		{
			char temp=a[i];
			a[i]=a[j];
			a[j]=temp;
		}
		
		private static boolean less(char ele1, char ele2)
		{
			return ele1 < ele2;
		}
		
		@Override
		public Iterator<String> iterator()
		{
			return new Iterator<String>() {
				private int n=0;

				@Override
				public boolean hasNext() {
					return n<nuts.length;
					
				}

				@Override
				public String next() {
					return bolts[n] + "- >" + nuts[n++];
					
				}
				
				
			};
					
		}
		
		public static void main(String args[])
		{
			char nuts[]=new char[] {'a','b','c','d'};
			char bolts[]=new char[]{'d','c','b','a'};
			
			NutsAndBolts obj=new NutsAndBolts(nuts,bolts);
			
			for(String pair:obj)
			{
				System.out.println(pair);
			}
			
		}
}