//71066001-陈伟杰
module alu(
    input [31:0] A,
    input [31:0] B,
    input [2:0] ALUOp,
    output [31:0] C
    );
	 reg [31:0] Cc;
	 assign C=Cc;
   
        always @(*) begin
            case (ALUOp)
                3'b000: Cc = A + B;
                3'b001: Cc = A - B;
                3'b010: Cc = A & B;
                3'b011: Cc = A | B;
                3'b100: Cc = A >> B;
                3'b101: Cc =$signed(A) >>> B;
            endcase
        end
endmodule