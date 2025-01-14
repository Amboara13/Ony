
using System;
using NCalc;

namespace Aff
{
    public static class Curve
    {
        public static double EvaluateFunction(string expression, double x)
        {
            try
            {
                // Utilisation de NCalc pour évaluer l'expression
                Expression e = new Expression(expression);
                e.Parameters["x"] = x;
                return Convert.ToDouble(e.Evaluate());
            }
            catch
            {
                throw new Exception("Erreur dans l'expression.");
            }
        }
    }
}