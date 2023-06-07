module code(
    input clk,
	 input reset,
	 input up_down,
	 output reg [7:0] out = 0//初始时计值为 0
    );
	 
	 always@(posedge clk)//同步复位
	 begin
	     if(reset)
		      out <= 0;//若reset为1，将计数器的值重置为8'b0
		  else
		      begin
				    if(up_down)
					     out <= out + 1;
					 else
					     out <= out - 1;
				end
	 end
	 
	 


endmodule

