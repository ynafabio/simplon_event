
<c:if test="${empty sessionPublic}">
<c:if test="${!empty param.p}">
	</body>
</c:if>
<c:if test="${empty param.p}">
<!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2022-2022 <a href="#">SIMPLON Côte dIvoire</a>.</strong> Tous droits reservés.
  </footer>
</div>
</c:if>

</c:if>

<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->


<!-- jQuery -->
<script src="<c:url value="/plugins/jquery/jquery.min.js"/>"></script>
<!-- Bootstrap 4 -->
<script src="<c:url value="/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- Select2 -->
<script src="<c:url value="/plugins/select2/js/select2.full.min.js"/>"></script>
<!-- Bootstrap4 Duallistbox -->
<script src="<c:url value="/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"/>"></script>
<!-- InputMask -->
<script src="<c:url value="/plugins/moment/moment.min.js"/>"></script>
<script src="<c:url value="/plugins/inputmask/jquery.inputmask.min.js"/>"></script>

<!-- date-range-picker -->
<script src="<c:url value="/plugins/daterangepicker/daterangepicker.js"/>"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<c:url value="/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"/>"></script>

<!-- dropzonejs -->
<script src="<c:url value="/plugins/dropzone/min/dropzone.min.js"/>"></script>

<!-- DataTables  & Plugins -->
<script src="<c:url value="/plugins/datatables/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-responsive/js/dataTables.responsive.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-buttons/js/dataTables.buttons.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"/>"></script>
<script src="<c:url value="/plugins/jszip/jszip.min.js"/>"></script>
<script src="<c:url value="/plugins/pdfmake/pdfmake.min.js"/>"></script>
<script src="<c:url value="/plugins/pdfmake/vfs_fonts.js"/>"></script>
<script src="<c:url value="/plugins/datatables-buttons/js/buttons.html5.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-buttons/js/buttons.print.min.js"/>"></script>
<script src="<c:url value="/plugins/datatables-buttons/js/buttons.colVis.min.js"/>"></script>

<!-- AdminLTE App -->
<script src="<c:url value="/dist/js/adminlte.js"/>"></script>


<!-- Page specific script -->
<script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true, 
      "lengthChange": false, 
      "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true  
    });
  });
  $(document).ready(function() {
	    $('#example3').DataTable( {
	        "language": {
	            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
	        }
	    } );
	} );
</script>
<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Initialize Select2 Elements
    $('.select2bs4').select2({
      theme: 'bootstrap4'
    })

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date picker
     $('#reservationdate').datetimepicker({
        format: 'DD/MM/YYYY',
        locale: 'fr',
        language: moment.locale('fr')
    }); 
    
  //Date picker
    $('#reservationdate1').datetimepicker({
        format: 'DD/MM/YYYY',
        locale: 'fr',
        language: moment.locale('fr')
    });
  //Date picker
    $('#reservationdate2').datetimepicker({
        format: 'DD/MM/YYYY',
        locale: 'fr',
        language: 'fr'
    });
    
    //Date and time picker
    $('#reservationdatetime').datetimepicker({ icons: { time: 'far fa-clock'} });

    //Date range picker
    $('#reservation').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        }
    })
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({
      timePicker: true,
      timePickerIncrement: 30,
      locale: {
        format: 'MM/DD/YYYY hh:mm A'
      }
    })
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Timepicker
    $('#timepicker').datetimepicker({
      format: 'LT'
    })
   
    
    //Bootstrap Duallistbox
    $('.duallistbox').bootstrapDualListbox()

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    $('.my-colorpicker2').on('colorpickerChange', function(event) {
      $('.my-colorpicker2 .fa-square').css('color', event.color.toString());
    })

    $("input[data-bootstrap-switch]").each(function(){
      $(this).bootstrapSwitch('state', $(this).prop('checked'));
    })

  })
 
</script>

</body>
</html>