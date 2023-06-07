module comparator (
    input [3:0] A,
    input [3:0] B,
    output reg O
);
    reg flag =0;
    always @ (A or B)
begin
  if (A[4]==0 && B[4]==0)
    begin
        A[3:0]
    end
end
endmodule




