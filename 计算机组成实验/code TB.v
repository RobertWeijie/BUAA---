module Testbench;

	// Inputs
	reg clk;
	reg reset;
	reg up_down;

	// Outputs
	wire [7:0] out;

	// Instantiate the Unit Under Test (UUT)
	code uut (
		.clk(clk),
		.reset(reset),
		.up_down(up_down),
		.out(out)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		reset = 0;
		up_down = 1;

		// Wait 100 ns for global reset to finish
		#100;
		up_down = 1;
		#20;
      reset = 1;
	    #20;
		reset = 0;
	end
      always #10 clk = ~clk;
endmodule

