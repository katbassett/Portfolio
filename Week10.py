## Calculate the number of CPU's used

def calculate():
    import multiprocessing
    print(multiprocessing.cpu_count())
 
user_input = input ('Would you like to know how many CPUs are in your device (yes/no): ' )
if user_input.lower() == 'yes':
    print(calculate())
elif user_input.lower() == 'no':
    print('No worries, catch you next time')
else:
    print('Type yes or no')