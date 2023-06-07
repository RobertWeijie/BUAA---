module splitter(
    input [31:0] A,
    output [7:0] O1,
    output [7:0] O2,
    output [7:0] O3,
    output [7:0] O4
    );
    assign O1[7] = A[24];
    assign O1[6] = A[25];
    assign O1[5] = A[26];
    assign O1[4] = A[27];
    assign O1[3] = A[28];
    assign O1[2] = A[29];
    assign O1[1] = A[30];
    assign O1[0] = A[31];	 

    assign O2[7] = A[16];
    assign O2[6] = A[17];
    assign O2[5] = A[18];
    assign O2[4] = A[19];
    assign O2[3] = A[20];
    assign O2[2] = A[21];
    assign O2[1] = A[22];
    assign O2[0] = A[23];

    assign O3[7] = A[8];
    assign O3[6] = A[9];
    assign O3[5] = A[10];
    assign O3[4] = A[11];
    assign O3[3] = A[12];
    assign O3[2] = A[13];
    assign O3[1] = A[14];
    assign O3[0] = A[15];	

    assign O4[7] = A[0];
    assign O4[6] = A[1];
    assign O4[5] = A[2];
    assign O4[4] = A[3];
    assign O4[3] = A[4];
    assign O4[2] = A[5];
    assign O4[1] = A[6];
    assign O4[0] = A[7];
	 
    endmodule
   
