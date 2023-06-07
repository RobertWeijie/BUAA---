module expr(
    input clr,
    input clk,
    
    input [7:0] in,
    output reg out = 0
    );


	integer state = 0;
	always@(posedge clk, posedge clr) 
    begin
		if(clr) begin//当时钟开始 初始状态和输出为0
			out = 0;
			state = 0;
		end
        else begin
			case(state) 
				0:
					begin
						if(in>="0"&&in<="9") 
                        begin//表达式F中只含有数字0-9
							state = 1;
							out = 1;
						end
						else begin
							state = 3;
							out = 0;
						end
					end
                1:
                    begin
                        if(in=="*"||in=="+") 
                        begin//加号+，乘号*
                            state = 2;
                            out = 0;
                        end
                        else begin
                            state = 3;
                            out = 0;
                        end
                    end
                2:
                    begin
                        if(in>="0"&&in<="9") 
                        begin
                            state = 1;//此时状态为1
                            out = 1;
                        end
                        else begin
                            state = 3;
                            out = 0;
                        end
                    end
                3:
                    begin
                        state = 3;
                        out = 0;
                    end
                    default:
                        begin
                            state = 0;
                            out = 0;
                        end

                                endcase
                            end
                        end
                            endmodule



