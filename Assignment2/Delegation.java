public class Delegation {
	public static void main(String args[]) {
		C c = new C();
		System.out.println(c.r());
		D d = new D();
		System.out.println(d.r());
		
		C2 c2 = new C2();
		System.out.println(c2.r());
		D2 d2 = new D2();
		System.out.println(d2.r());	
	}
}

abstract class A {
	int a1 = 1;
	int a2 = 2;

	public int f() {
		return a1 + p(100) + q(100);
	}
	
    protected abstract int p(int m);
    protected abstract int q(int m);
 }

 
 class B extends A {
	int b1 = 10;
	int b2 = 20;

	public int g() {
		return f() + this.q(200);
	}

	public int p(int m) {
		return m + b1;
	}

	public int q(int m) {
		return m + b2;
	}
}
 

class C extends B {
	int c1 = 100;
	int c2 = 200;

	public int r() {
		return f() + g() + c1;
		}
	
	public int p(int m) {
		return super.p(m) + super.q(m) + c2;
	}
	
	public int q(int m) {
		return m + a2 + b2 + c2;
	}
}

class D extends B {
	int d1 = 300;
	int d2 = 400;
	
	public int p(int m) {
		return m + a1 + b1 + d1;
		
	}
	public int r() {
		return f() + g() + d2;
	}

}

interface IA{
	int f();
	int p(int m);
	int q(int m);
	int get_a1();
	int get_a2();
}

interface IB extends IA{
	int g();
	int get_b1();
	int get_b2();
}

interface IC extends IB{
	int r();
}

interface ID extends IB{
	int r();
}


class A2 implements IA {
	int a1 = 1;
	int a2 = 2;
	
	public A2(IA a){
		thisa2 = a; 
	}

	public int f() {
		return a1 + p(100) + q(100);
	}

    public int p(int m){
    	return thisa2.p(m);
    }
    
    public int q(int m){
    	return thisa2.q(m);
    }
    
    public int get_a1(){
    	return a1;
    }
    
    public int get_a2(){
    	return a2;
    }
    
    
    IA thisa2;
 }

class B2 implements IB {
	int b1 = 10;
	int b2 = 20;

	public B2(IB b){
		thisb2 = b;
		supera2 = new A2(b);
	}
	public int g() {
		return f() + thisb2.q(200);
	}

	public int p(int m) {
		return m + b1;
	}

	public int q(int m) {
		return m + b2;
	}
	public int get_a1(){
    	return supera2.get_a1();
    }
	public int get_a2(){
    	return supera2.get_a2();
    }
	public int get_b1(){
    	return b1;
    }
	public int get_b2(){
    	return b2;
    }
	
	public int f() {
		return supera2.f();
	}
	
	IA supera2;
	IB thisb2;
}

class C2 implements IC {
	int c1 = 100;
	int c2 = 200;

	public C2(){
		superb2= new B2(this);
	}

	public int g() {
		return superb2.g();
	}
	
	public int f() {
		return superb2.f();
	}
	
	public int r() {
		return f() + g() + c1;
		
	}
	
	public int p(int m) {
		return superb2.p(m) + superb2.q(m) + c2;
	}
	public int get_a1(){
    	return superb2.get_a1();
    }
	public int get_b1(){
    	return superb2.get_b1();
    }
	public int get_a2(){
    	return superb2.get_a2();
    }
	public int get_b2(){
    	return superb2.get_b2();
    }
	
	public int q(int m) {
		return m + get_a2() + get_b2() + c2;
	}
	
	IB superb2;
}

class D2 implements ID {
	int d1 = 300;
	int d2 = 400;

	public D2(){
		superb2= new B2(this);
	}
	
	public int g() {
		return superb2.g();
	}
	
	public int f() {
		return superb2.f();
	}
	public int get_a1(){
    	return superb2.get_a1();
    }
	
	public int get_b1(){
    	return superb2.get_b1();
    }
	public int get_a2(){
    	return superb2.get_a2();
    }
	
	public int get_b2(){
    	return superb2.get_b2();
    }
	
	public int p(int m) {
		return m + get_a1() + get_b1() + d1;
		
	}
	
	public int r() {
		return f() + g() + d2;
	}
	
	public int q(int m){
		return superb2.q(m);
	}
	IB superb2;
}