/*
 * ����������   �̳��Գ�����Bits
 * ����Bits_realize �ǳ�����Bits�ľ���ʵ��
 * ��public String toString()//�������ӡΪ����01�ַ�����   //�����˲���   �ĳ���   public String toString(byte[] m_bits) //�������ӡΪ����01�ַ�����
 * ��public Bits copy() //����     //�����˲���   �ĳ���    public Bits copy(Bits B_bit)   
 *  ����δ�޸ĳ������е���������
 *  
 *  �޸���   ף��
 * liug@ScitLiugTeam 2020.7.12
 */

package com.ScitLiugTeam.auto_scheduling;


/**
 *  �̳��Գ�����Bits���ǳ�����Bits�ľ���ʵ�֣�
 * @author ף��
 */


public class Bits_realize extends Bits
{
	public static int[] binary_system(int n_system1,int n_digit1,int n_system2)//ת������  ����һ��int������ʾ�Ľ���ת�� Ϊ�ڶ��� int������ʾ�Ľ�����   ������int[]���� 
	{
		
		String str=""+n_digit1;
		int nI=Integer.parseInt(str,n_system1);//n_system1ת��Ϊʮ����
		int nJ=Integer.parseInt(Integer.toString(nI, n_system2));//ת��Ϊn_system2����
		System.out.println(""+n_system2+"�������ǣ�"+nJ);
		int nDigit1Places = 0;//λ��
		int nNum1=nJ;//�����ñ���1 
		int nNum2=n_system2;//�����ñ���2
		while(nNum1!=0)
		{
			nNum1=nNum1/10;
			nDigit1Places++;
		}
		System.out.println("λ���ǣ�"+nDigit1Places);
		int[] nDigit1s = new int[nDigit1Places];
		int i = 0;//ѭ������
		int nNum3=nJ;//�����ñ���3 
		int nNum4=0;//�����ñ���4
		while(nNum3!=0)
		{
			nDigit1s[i]=nNum3%10;
			nNum3=nNum3/10;
			i++;
		}
		return nDigit1s;
	}

	public byte[] newBits(int n) 
	{
		byte[] mNewbits = new byte[n];//һ���ֽ� Ϊ ��λ  ���½�����Ϊn��λ����   ��Ϊǰ�����ֽ��ǣ�Ҳ����2��byte��һ�����������������ʾm_bits������ܳ��ȣ�û�б�Ҫ��
		//java����.length���������Կ��Ի�ȡ���鳤��
		return mNewbits;
	}
	
	
	public byte[] fromString(String sBits) //����01�ַ����½����������У��ַ����ǰ�����HEAD��////////////////
	{
		
		if(sBits.length()==0)
			return null;
		if(sBits.length()<8)
		{
			byte[] mBits = new byte[1];
			byte mN=0;
			int n=  Integer.parseInt(sBits.substring(0,sBits.length()));
			System.out.println("         "+Biannary2Decimal(n));
			//String result = Integer.toBinaryString(Biannary2Decimal(n));
			byte[] m_bits2=intToByte4(Biannary2Decimal(n));
			m_bits[0]=m_bits2[3];
			return m_bits;
		}
		
		if(sBits.length()>=8)
		{
			double k=sBits.length()/8;
			if(k%1!=0)
			{
				byte[] mBits = new byte[sBits.length()/8+1];
			}
			else
			{
				byte[] mBits = new byte[sBits.length()/8];
			}
		}
		
		System.out.println("\n\n"+m_bits.length);
		int nBeginIndex=0,nEndIndex=8;
		int nLen = sBits.length();
		for(int i=0;i<nLen/8;i++)
		{
			byte n2=0;
			
			/*
			int n= Integer.parseInt(sBits.substring(n_beginIndex,n_EndIndex));
			System.out.println(n);
			//n2+= n * Math.pow(10,j);
			n_beginIndex+=1;
			n_EndIndex+=1;
			*/
			int n= Integer.parseInt(sBits.substring(nBeginIndex,nEndIndex));
			n2=(byte) n;
			System.out.println(n);
			nBeginIndex+=8;
			nEndIndex+=8;
			if(nEndIndex>nLen)
			{
				return null;
			}
			m_bits[i]=n2;
		}
		
		
		
		
		
		return m_bits;
	}

	@Override
	public int length() //����Ҫ
	{
		// TODO �Զ����ɵķ������
		return 0;
	}

	public Bits copy(Bits B_bit) 
	{
		//m_bits
		for(int i=0;i<B_bit.m_bits.length;i++)
		{
			B_bit.m_bits[i]=m_bits[i];
		}
		
		return B_bit;
	}

	@Override//����p1������������p2��������λ֮���ֵ��ΪnVal������������ȷ��p2>p1>=0����λ���㹻�����������
	public void assign(int nP1, int nP2, int nVal) //p1��p2��ʾ���Ƕ�����ʵ���е�λ�ã�������HEAD//////?
	{
		if(nP2>m_bits.length)
		{
			return ;
		}
		if(nP2>nP1&&nP1>=0)
		{
			byte[] mBit=intToByte4(nVal);
			int i=nP2-nP1;
			if(i<mBit.length)
			{
				return ;
			}
			for(int j=0;j<i;j++)
			{
				m_bits[nP1+j]=mBit[j];
			}
		}
		else 
		{
			return ;
		}
	}

	public int getValue(int nP1, int nP2) //��ȡ��p1������������p2��������λ֮���ֵ/////////
	{
		//m_bits;
		/*
		int num = 0;
		byte[] m_bits2 = new byte[nP2-nP1+1];
		int j=0;
		for(int i=nP1;i<=nP2;i++)
		{
			m_bits2[j]=m_bits[nP1];
			j++;
		}
		
		*/
		if(nP2>m_bits.length)//�ж��Ƿ��������
		{
			return -1;
		}
		
		byte[] mNums = new byte[nP2-nP1];
		for(int i=nP1;i<nP1+nP2;i++)
		{
			mNums[i-nP1]=m_bits[i];
		}
		int nNum = 1;
		for(int i=0;i<mNums.length;i++)
		{
			nNum*=mNums[i];
		}
		
		
		
		
		
		return nNum;
	}

	@Override
	public void reverse(int nP1, int nP2) //����p1������������p2��������λ֮���ֵ����//
	{
		//m_bits
		if(nP1<=nP2&&nP2<=m_bits.length)//�ж��Ƿ��������
		{
			return ;
		}
		byte[] mBits2 =new byte[nP2-nP1+1];
		int j=0;
		for(int i=nP1;i<=nP2;i++)
		{
			mBits2[j]=m_bits[nP1];
			j++;
		}
		for(int i=nP1;i<=nP2;i++)
		{
			m_bits[nP1]=mBits2[j];
			j--;
		}
	}

	@Override
	public int setBit(int nP, int nBit) //����pλ��Ϊ0��1��nBitֻȡ0��1//////?
	{
		if(nBit==1||nBit==0)//�ж��Ƿ��������
		{
			m_bits[nP]=(byte) nBit;
			return 0;
		}
		
		return -1;
	}

	@Override
	public void negate(int nP) //����pλȡ��
	{
		if(nP>m_bits.length)
		{
			return ;
		}
		m_bits[nP]=(byte) (~m_bits[nP]);
	}

	@Override
	public byte[] cross(Bits bitsA, Bits bitsB, int nP1, int nP2)//����Bits���󽻻�d��p1λ����p2λ֮�������
	{
		if(nP2<nP1)//�ж��Ƿ��������
		{
			return null;
		}
		byte[] mBits2 =new byte[nP2-nP1+1];
		int nBitsa=bitsA.m_bits.length;
		int nBitsb=bitsA.m_bits.length;
		if(nP2>nBitsa||nP2>nBitsb)//�ж��Ƿ��������
		{
			return null;
		}
		int j=0;
		for(int i=nP1;i<=nP2;i++)
		{
			mBits2[j]=bitsA.m_bits[nP1];
			bitsA.m_bits[nP1]=bitsB.m_bits[nP1];
			bitsB.m_bits[nP1]=mBits2[j];
			j++;
		}
		
		return mBits2;
	}

	@Override
	public void cross(Bits bitsA, Bits bitsB, int nP)//����Bits�����pλ���档�����㷨�ǣ�����������A������������B
	{
		int nBitsa=bitsA.m_bits.length;
		int nBitsb=bitsB.m_bits.length;
		byte nNum;
		if(nP>nBitsa||nP>nBitsb)//�ж��Ƿ��������
		{
			return ;
		}
		nNum=(byte) ((bitsA.m_bits[nP])&(bitsB.m_bits[nP]));
		bitsB.m_bits[nP]=(byte) ((bitsA.m_bits[nP])|(bitsB.m_bits[nP]));
		bitsA.m_bits[nP]=nNum;
	}

	public String toString(byte[] m_bits) //�������ӡΪ����01�ַ�����
	{
		if(m_bits.length==0)//�ж��Ƿ��������
		{
			return "";
		}
		 //if (m_bits == null || m_bits.length != 4)
		// {
	    //        throw new IllegalArgumentException("byte������벻Ϊ��,������4λ!");
	    //   }
		//Integer.toBinaryString(1088)
		String sStr="";//����������
		for(int i=m_bits.length-1;i>=0;i--)
		{
			String sStr2=Integer.toBinaryString((int)m_bits[i]);
			if(sStr2.length()<8)
			{
				for(int j=0;j<=8-sStr2.length();j++)
				{
					sStr2="0"+sStr2;
				}
			}
			sStr=sStr+sStr2;
			
		}
		
		
		
		return sStr;
	}
	
	
	
	public static void main(String[] args) 
	{
		//int l=0b11;
		//System.out.println(p);
		
		
		Bits_realize n = new Bits_realize();
		n.m_bits=n.newBits(5);
		n.m_bits[0]=(byte) 011;
		
		System.out.println(n.m_bits[0]);
		
		System.out.println("�ֽ���: "+n.m_bits.length);

		System.out.println("������λ: "+n.m_bits.length*16);
		
		int[] k=binary_system(10,14,2);
		
		for(int i=k.length-1;i>=0;i--)
			System.out.println(k[i]);
		
		n.m_bits[0]=13;
		n.m_bits[1]=53;
		n.m_bits[2]=43;
		
		System.out.println("0��1����������"+n.getValue(0,2));
		
		
		byte[]  h=n.fromString("111111");
		System.out.println(h.length);
		for(int i=0;i<h.length;i++)
			System.out.println(h[i]);
		
		
		byte[] oo = new byte[2];
		oo[0]=43;
		oo[1]=53;
		String l=n.toString(oo);
		System.out.println(l);
		
		
		
		
		
		

	}

	
	
	public  static  Integer Biannary2Decimal(int bi)//�������Ƶ�intת��Ϊʮ����int
	{
        String sBinStr = bi+"";
        Integer sum = 0;
        int nLen = sBinStr.length();
        for (int i=1;i<=nLen;i++){
            //��iλ ������Ϊ��
            int dt = Integer.parseInt(sBinStr.substring(i-1,i));
            sum+=(int)Math.pow(2, nLen-i)*dt;
        }
        return  sum;
    }
	
	public static byte[] intToByte4(int sum) //��int�ͱ���ת��Ϊbyte����
	{
        byte[] mArr = new byte[4];
        mArr[0] = (byte) (sum >> 24);
        mArr[1] = (byte) (sum >> 16);
        mArr[2] = (byte) (sum >> 8);
        mArr[3] = (byte) (sum & 0xff);
        return mArr;
    }

	@Override
	public String toString() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public Bits copy() {
		// TODO �Զ����ɵķ������
		return null;
	}
	
	
	
}
