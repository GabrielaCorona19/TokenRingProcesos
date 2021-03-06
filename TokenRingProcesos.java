import mpi.*;
public class TokenRingProcesos {

 public static void main(String args[]) throws Exception {
        int me;
        int size;
        int[] buf = {1001};

        MPI.Init(args);

        me = MPI.COMM_WORLD.Rank();
        size = MPI.COMM_WORLD.Size();


		if (me == 0) {
			MPI.COMM_WORLD.Send(buf, 0, 1, MPI.INT, 1, 17);
			
			while(true){
				MPI.COMM_WORLD.Recv(buf, 0, 1, MPI.INT, size-1, 17);
				System.out.println("Soy "+me +": "+buf[0]);
				MPI.COMM_WORLD.Send(buf, 0, 1, MPI.INT, 1, 17);
			}
			
		} else {
			while(true){
				if(me==(size-1)){
					MPI.COMM_WORLD.Recv(buf, 0, 1, MPI.INT, me-1, 17);
					System.out.println("Soy "+me +": "+buf[0]);
					MPI.COMM_WORLD.Send(buf, 0, 1, MPI.INT, 0, 17);
				}
				else{
					MPI.COMM_WORLD.Recv(buf, 0, 1, MPI.INT, me-1, 17);
					System.out.println("Soy "+me +": "+buf[0]);
					MPI.COMM_WORLD.Send(buf, 0, 1, MPI.INT, me+1, 17);
				}
			}
		}
		

        //MPI.Finalize();
    }
} 