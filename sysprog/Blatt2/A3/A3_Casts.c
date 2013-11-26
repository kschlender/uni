int main()
{
	double a;
	double b;
	double c;
	double d;
	double e;
	double f;
	
	a = (float)7/4;				/*1,75 Sinnvoll, aber nicht ganzen zahlenbereich genutzt*/
	b = (double)(7/4);			/*1,75 Falsch: (7/4) ergibt int 1, danach nach double gekastet*/
	c = (double)(7/(float)4);	/*1,75 Unnötig nach double gekastet, nach float ist richtig*/
	d = (double)7/4;			/*1,75 Sinnvoll Beste*/
	e = (double)(7/4.0);		/*1,75 Unnötig nach double gekastet, 4.0 ist double*/
	f = (double)7.0f/4;			/*1,75 Unnötig nach double gekastet*/
	
	printf("%f_%f_%f_%f_%f_%f",a,b,c,d,e,f);/*1.750000_1.000000_1.750000_1.750000_1.750000_1.750000*/
	
	return 0;
}
