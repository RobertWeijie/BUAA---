module ext(
    input [15:0] imm,
    input [1:0] EOp,
    output reg [31:0] ext);

    always @ (EOp,imm)
    begin
       if (EOp == 2'b00)
          ext = {{16{imm[15]}},imm[15:0]};
       else if (EOp == 2'b01)
          ext = {{16{1'b0}},imm[15:0]};
       else if (EOp == 2'b10)
          ext = {{16{1'b0}},imm[15:0]};
       else if (EOp == 2'b11)
          ext = {{14{imm[15]}},imm[15:0],1'b0}; 
    end
endmodule
//71066001-陈伟杰



