module Tb;

	// Inputs
	reg [31:0] A;

	// Outputs
	wire [7:0] O1;
	wire [7:0] O2;
	wire [7:0] O3;
	wire [7:0] O4;

	// Instantiate the Unit Under Test (UUT)
	splitter uut (
		.A(A),
		.O1(O1),
		.O2(O2),
		.O3(O3),
		.O4(O4)
	);

	initial begin
		// Initialize Inputs
		A = 32'h11223344;
      #100;
		A = 32'h55667788;		
      #100;
		A = 32'h11227744;	
      #100;		
	end
     
endmodule
