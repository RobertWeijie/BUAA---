#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int main()
{
    FILE *fp, *pp;
    fp = fopen("filein.txt", "r");
    pp = fopen("fileout.txt", "w");

    char in[20], out[20], ch, str[1050];
    scanf("%s%s", &in, &out);

    int len_in, len_out, i = 0, len_str, j = 0;
    len_in = strlen(in);
    len_out = strlen(out);

    ch = fgetc(fp);
    while (ch != EOF)
    {
        str[i++] = ch;
        ch = fgetc(fp);
    }
    len_str = i + 1;

    for (i = 0; i < len_str; i++)
    {
        int flag = 0;
        if (tolower(str[i]) == tolower(in[0]))
        {
            for (j = 1; j < len_in; j++)
            {
                if (tolower(str[i + j]) != tolower(in[j]))
                {
                    flag = 1;
                }
            }

            if (flag == 0)
            {
                for (int z = 0; z < len_out; z++)
                {
                    fputc(out[z], pp);
                }
                i = i + len_in - 1; //后移单词长度
            }

            else
            {
                fputc(str[i], pp);
            }
        }

        else
        {
            fputc(str[i], pp);
        }
    }

    fclose(fp);
    fclose(pp);
}
